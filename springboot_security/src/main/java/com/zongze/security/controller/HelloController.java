package com.zongze.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/8/28 18:57
 * @Created by xzz
 */
@RestController
@RequestMapping("hello")
public class HelloController {


    @PreAuthorize("hasRole('root')")
    @GetMapping
    public String hello(){
        return  "hello";
    }






}
