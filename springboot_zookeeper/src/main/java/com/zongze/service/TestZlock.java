package com.zongze.service;

import com.zongze.annotation.Zlock;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By xzz on 2019/12/23
 */
@Service
public class TestZlock {




    @Zlock("order")
    public void zlockTest() throws InterruptedException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "is stop"+format.format(new Date()));
    }




}
