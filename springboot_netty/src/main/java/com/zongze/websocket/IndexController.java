package com.zongze.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sound.midi.Soundbank;

/**
 * @Date 2020/9/4 17:36
 * @Created by xzz
 */
@Controller
public class IndexController {



    @RequestMapping("index")
    public String index(){
        return "websocket";
    }





}
