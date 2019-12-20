package com.zongze.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create By xzz on 2019/12/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void test() {
        for (int i=0;i<103;i++){
            String s = userController.test3();
            System.out.println(s);
        }
    }
}