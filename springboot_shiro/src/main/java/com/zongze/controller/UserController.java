package com.zongze.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zongze.config.shiro.UserRealm;
import com.zongze.entity.ExcelTest;
import com.zongze.entity.User;
import com.zongze.util.ExcelUtil;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create By xzz on 2018/12/24
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRealm userRealm;
    @Autowired
    private RedisSessionDAO redisSessionDAO;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("login")
    public Object login(@RequestBody User user, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
        subject.login(token);
        Session session = subject.getSession(false);
        System.out.println(JSON.toJSONString(session));

        return "success";
    }


    @RequestMapping("check")
    public Object check() throws InterruptedException {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("==============perm====================");
        System.out.println(subject.isPermitted("sys:menu:view"));
        System.out.println(subject.isPermitted("sys:menu:delete"));
        System.out.println(subject.isPermitted("sys:menu:list"));
        System.out.println(subject.isPermitted("sys:menu:add"));
        System.out.println("===================role===========");
        System.out.println(subject.hasRole("admin"));
        System.out.println(subject.hasRole("tou"));
        System.out.println(subject.hasRole("cus"));
        return "权限验证通过";
    }

    @RequestMapping("timeout")
    public Object timeout(){
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        userRealm.clearCache(principals);

        User user = new User();
        user.setUserName("zhangsan");
        user.setAge(48);
        user.setId(425l);
        user.setPassWord("854212");
        System.out.println(redisTemplate.opsForHash().get("shiro_session_wangwu", "token"));
        redisTemplate.opsForHash().put("shiro_session_wangwu", "entity",user);

        return null;

    }


    @PostMapping("clear")
    public Object check2() {
        Collection<Session> activeSessions = redisSessionDAO.getActiveSessions();
        logger.info("活跃用户数为:{};活跃用户为:{}", activeSessions.size(), JSON.toJSONString(activeSessions));
        return "缓存清除成功";
    }


    private AtomicInteger count = new AtomicInteger(0);

    @PostMapping("count")
    public Object count() {
        count.getAndIncrement();
        System.out.println(count.get());
        return "缓存清除成功";
    }


    @RequestMapping("excel")
    @ResponseBody
    public Object excel(HttpServletResponse response) {
        List<ExcelTest> list = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            ExcelTest test = new ExcelTest();
            test.setId(i);
            test.setName("test" + i);
            test.setBirthday(new Date());
            if (i == 10) {
                test.setMoney(null);
            } else {
                test.setMoney(0.225);
            }
            list.add(test);
        }
        ExcelUtil<ExcelTest> util = new ExcelUtil<>(ExcelTest.class);
        if (util.exportExcel(list, "代付日结算", response)) {
            return "success";
        } else {
            return "error";
        }

    }


    @PostMapping("rest")
    public ExcelTest getEntiry(@RequestBody ExcelTest text) {
        ExcelTest excelTest = new ExcelTest();
        excelTest.setId(text.getId());
        excelTest.setName("rest");
        excelTest.setBirthday(new Date());
        return excelTest;
    }


    @PostMapping("start")
    public ExcelTest proxy(@RequestBody ExcelTest text) {
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/user/rest", text, String.class);
        logger.info("response message {}", stringResponseEntity.getBody());
        ExcelTest test = JSONObject.parseObject(stringResponseEntity.getBody(), ExcelTest.class);
        logger.info("test对象：{}", JSON.toJSONString(test));
        return test;
    }


}
