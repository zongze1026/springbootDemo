package com.zongze.controller;

import java.util.concurrent.Callable;

/**
 * Create By xzz on 2019/7/1
 */
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("线程"+Thread.currentThread().getName()+"正在执行中");
        Thread.sleep(2000);
        return Thread.currentThread().getName();
    }
}
