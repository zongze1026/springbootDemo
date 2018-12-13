package com.zongze.controller;
import com.zongze.model.User;
import com.zongze.utils.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By xzz on 2018/12/13
 */
@RestController
@RequestMapping("user")
public class UserController {


    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(){
        User user = new User("zhangsan",18);
        RedisUtil.set("user",user,10);
        return "success";
    }






}
