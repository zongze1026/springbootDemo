package com.zongze.mybatisplug.service;

import com.zongze.mybatisplug.mapper.UserMapper;
import com.zongze.mybatisplug.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Date 2021/3/24 14:45
 * @Created by xiezz
 */
@Service
public class UserServiceImpl {



    @Autowired
    private UserMapper userMapper;


    public void test(){
        User user = userMapper.selectById(1);
        System.out.println(user.getName());
    }






}
