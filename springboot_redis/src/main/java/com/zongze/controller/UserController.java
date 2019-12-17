package com.zongze.controller;
import com.alibaba.fastjson.JSON;
import com.zongze.model.TestModel;
import com.zongze.model.User;
import com.zongze.service.EmailService;
import com.zongze.valid.Group1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Create By xzz on 2018/12/13
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private EmailService service;


    @PostMapping("/test")
    public Object test(@RequestBody User user){
        System.out.println(JSON.toJSONString(user));
        return user;
    }


    @PostMapping("/tes1")
    public Object test1(@RequestBody @Valid TestModel user){
        return user;
    }

    @GetMapping("/tes2")
    public void test2() throws InterruptedException {
        service.testLock();
    }







}
