package com.zongze.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
