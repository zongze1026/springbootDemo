package com.zongze.service;

import com.zongze.annotation.Zlock;
import org.springframework.stereotype.Service;

/**
 * Create By xzz on 2019/12/23
 */
@Service
public class TestZlock {




    @Zlock("order")
    public void zlockTest() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "is running");
        System.out.println(Thread.currentThread().getName() + "is stop");
    }




}
