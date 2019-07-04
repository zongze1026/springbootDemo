package com.zongze.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Create By xzz on 2019/7/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WXServiceTest {

    @Autowired
    WXService wxService;

    @Test
    public void createMenu() {
        boolean menu = wxService.createMenu();
        System.out.println(menu);
    }
}