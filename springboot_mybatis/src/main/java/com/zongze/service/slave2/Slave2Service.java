package com.zongze.service.slave2;

import com.alibaba.fastjson.JSON;
import com.zongze.annotation.DynamicDatasource;
import com.zongze.domain.User;
import com.zongze.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By xzz on 2018/11/27
 */
@Service
public class Slave2Service {


    @Autowired
    private UserMapper userMapper;

    @DynamicDatasource("master")
    public void add(User user) {
        userMapper.add(user);
    }

    public void userInfo() {
        User user = userMapper.find();
        System.out.println(JSON.toJSONString(user));
    }

    public User user() {
        return userMapper.find();
    }


}
