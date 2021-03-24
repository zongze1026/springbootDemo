package com.zongze.mybatisplug.controller;

import com.zongze.mybatisplug.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2021/3/24 15:27
 * @Created by xiezz
 */
@RestController
public class MybatisPlugsController {



    @Autowired
    private UserServiceImpl userService;

    @GetMapping("test")
    public void test(){
        userService.test();
    }



}
