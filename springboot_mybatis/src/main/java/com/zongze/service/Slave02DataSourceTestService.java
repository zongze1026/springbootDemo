package com.zongze.service;

import com.zongze.annotation.DynamicDatasource;
import com.zongze.domain.User;
import com.zongze.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create By xzz on 2020/6/18
 */
@Service
public class Slave02DataSourceTestService {


    @Autowired
    private UserMapper userMapper;


    @DynamicDatasource("slave02")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addUser(User user){
        return userMapper.add(user);
    }





}
