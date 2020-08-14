package com.zongze.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池源码
 *
 * @Date 2020/8/5 13:00
 * @Created by xzz
 */
public class ThreadPoolSource {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }





}
