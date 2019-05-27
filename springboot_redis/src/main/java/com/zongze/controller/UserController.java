package com.zongze.controller;
import com.zongze.model.TestModel;
import com.zongze.model.User;
import com.zongze.valid.Group1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @PostMapping("/test")
    public Object test(@RequestBody @Validated({Group1.class}) User user){
        return user;
    }


    @PostMapping("/tes1")
    public Object test1(@RequestBody @Valid TestModel user){
        return user;
    }







}
