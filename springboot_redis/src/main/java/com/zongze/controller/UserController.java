package com.zongze.controller;

import com.alibaba.fastjson.JSON;
import com.zongze.model.User;
import com.zongze.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Create By xzz on 2018/12/13
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add() {
        User user = new User("zhangsan", 18l);
        RedisUtil.set("user", user, 60);

        logger.info("info=" + JSON.toJSONString(user));
        logger.debug("debug=" + JSON.toJSONString(user));
        logger.error("error=" + JSON.toJSONString(user));
        logger.warn("warn=" + JSON.toJSONString(user));
        logger.trace("trace=" + JSON.toJSONString(user));
        return "success";
    }


    @PostMapping("expire")
    public String key(@RequestBody User u) {
        List<User> lists = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(i);
            lists.add(user);
        }
        Long aLong = RedisUtil.leftPushAll("list:user", lists);
        List<User> users = RedisUtil.range("list:user",0,-1);
        System.out.println(users.size());
        users.stream().forEach(ele->{
            System.out.println(JSON.toJSONString(ele));
        });
        return "success";
    }

    @GetMapping("qr")
    public Object qrLink(HttpServletRequest request) {
        String age = request.getParameter("age");
        String name = request.getParameter("name");
        User user = new User();
        user.setAge(Long.valueOf(age));
        user.setUserName(name);
        return user;
    }


    public static void main(String[] args) {
        int i = 0;
        do {
            System.out.println(i);
            i++;
        } while (i < 10);
    }


}
