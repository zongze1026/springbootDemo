package com.zongze.security.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/8/28 18:57
 * @Created by xzz
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping("detail")
    public String roleDetail(){
        return "this is admin detail";
    }






}
