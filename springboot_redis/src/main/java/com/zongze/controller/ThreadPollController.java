package com.zongze.controller;


import com.zongze.entity.ResultResp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Create By xzz on 2018/12/24
 */
@Controller
public class ThreadPollController {


    @GetMapping("/pool/test")
    @ResponseBody
    public ResultResp pool() throws ExecutionException, InterruptedException {
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new Task());
        }
        List<Future<String>> futures = PoolHolder.submit(tasks);
        for (Future future : futures) {
            System.out.println(future.get());
        }
        return ResultResp.succ();
    }


    @GetMapping("/pool/test03")
    @ResponseBody
    public void test03() throws ExecutionException, InterruptedException {
        List<Future<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            tasks.add(PoolHolder.submit(new Task()));
        }
        System.out.println(tasks.size());
        for (Future future : tasks) {
            System.out.println(future.get());
        }
    }


    @GetMapping("/pool/test2")
    @ResponseBody
    public ResultResp aa() {
        System.out.println("======================");
        return ResultResp.succ();
    }


    static class PoolHolder {

        private static ThreadPoolExecutor executor;


        static {
            initPool();
        }

        public static void initPool() {
            System.out.println("初始化线程池！");
            ThreadPoolExecutor threadExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
            threadExecutor.setKeepAliveTime(5000, TimeUnit.MILLISECONDS);
            threadExecutor.allowCoreThreadTimeOut(true);
            executor = threadExecutor;
        }


        public static List<Future<String>> submit(List<Callable<String>> tasks) {
                try {
                System.out.println("线程核心数量：" + executor.getCorePoolSize());
                System.out.println("pool数量：" + executor.getPoolSize());
                return executor.invokeAll(tasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Future<String> submit(Callable<String> tasks) {
            try {
                if (null == executor) {
                    initPool();
                    System.out.println("线程核心数量：" + executor.getCorePoolSize());
                    System.out.println("pool数量：" + executor.getPoolSize());
                    return executor.submit(tasks);
                } else {
                    System.out.println("线程核心数量：" + executor.getCorePoolSize());
                    System.out.println("pool数量：" + executor.getPoolSize());
                    return executor.submit(tasks);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


    }


}
