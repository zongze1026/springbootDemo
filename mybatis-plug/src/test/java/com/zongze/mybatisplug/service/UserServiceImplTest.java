package com.zongze.mybatisplug.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @Date 2021/3/24 14:54
 * @Created by xiezz
 */
@SpringBootTest
public class UserServiceImplTest {


    @Autowired
    private UserServiceImpl userService;


    @Test
    public void test1() {
        userService.test();
    }
}