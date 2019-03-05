package com.zongze.controller;

import com.alibaba.fastjson.JSON;
import com.zongze.model.User;
import com.zongze.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By xzz on 2018/12/13
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add() {
        User user = new User("zhangsan", 18);
        RedisUtil.set("user", user, 10);

        logger.info("info=" + JSON.toJSONString(user));
        logger.debug("debug=" + JSON.toJSONString(user));
        logger.error("error=" + JSON.toJSONString(user));
        logger.warn("warn=" + JSON.toJSONString(user));
        logger.trace("trace=" + JSON.toJSONString(user));
        return "success";



    }


    public static void main(String[] args) {
        int i = 0;
        do{
            System.out.println(i);
            i++;
        }while (i<10);
    }


}
