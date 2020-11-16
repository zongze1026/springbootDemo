package com.zongze.freemarker.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/11/11 11:18
 * @Created by xiezz
 */
@RestController
public class VliadatedTestController{


    @PostMapping("/validTest")
    public String valid(@Validated User user){
        return JSON.toJSONString(user);
    }


}
