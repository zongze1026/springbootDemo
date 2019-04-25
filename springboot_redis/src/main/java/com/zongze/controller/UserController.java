package com.zongze.controller;

import com.alibaba.fastjson.JSON;
import com.zongze.model.User;
import com.zongze.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        RedisUtil.leftPushAll("a", strings);
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
        try {
            runTimeEx();
        } catch (Exception e) {
            System.out.println("==========出现异常================");
        }
    }

    private static void runTimeEx() {
        String timeString = "18:00-08:00";
        String[] split = timeString.split("-");
        Date start = null;
        Date end = null;
        Calendar calendar = Calendar.getInstance();
        String[] datestring = split[0].split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(datestring[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(datestring[1]));


    }


}
