package com.zongze.controller;

import com.zongze.service.TestZlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By xzz on 2019/12/23
 */
@RestController
@RequestMapping("test")
public class TestZlockController {

    @Autowired
    private TestZlock testZlock;

    @GetMapping("zlock")
    public void test() {
        for (int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        testZlock.zlockTest();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }




}
