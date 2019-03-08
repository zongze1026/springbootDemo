package com.zongze.service;

import com.github.pagehelper.PageInfo;
import com.zongze.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By xzz on 2019/3/8
 */
@Service
public class pService {


    @Autowired
    private UserService userService;


    public Object pageTest(User user){
        PageInfo<User> pageinfo = userService.userList(user);
        Map<String,Object>list = new HashMap<>();
        list.put("uList",pageinfo);
        list.put("user", userService.userInfo(user.getUserName()));
       return list;

    }






}
