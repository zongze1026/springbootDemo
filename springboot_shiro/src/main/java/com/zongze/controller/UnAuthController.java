package com.zongze.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create By xzz on 2019/1/30
 */
@Controller
public class UnAuthController {

    @RequestMapping("unAuth")
    public String unAuth(){
        return "unAuth";
    }



}
