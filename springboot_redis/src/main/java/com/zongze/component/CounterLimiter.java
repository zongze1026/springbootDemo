package com.zongze.component;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * ip限流
 * Create By xzz on 2019/12/19
 */
@Component
public class CounterLimiter {

    private ConcurrentHashMap<String, IpCounterInfo> counterContext = new ConcurrentHashMap<>();

    /**
     * 执行限流方法，返回true允许访问
     *
     * @param:
     * @return:
     */
    public boolean doLimit(String ip) {
        return getCounter(ip).isAllow();
    }


    /**
     * 从counterContext查找ip实体
     * 如果没有则创建一个新的，同时放入容器
     *
     * @param:
     * @return:
     */
    public IpCounterInfo getCounter(String ip) {
        IpCounterInfo counter;
        if (ObjectUtils.isEmpty(counter = counterContext.get(ip))) {
            counter = IpCounterInfo.getCounter(ip, counterContext);
        }
        return counter;
    }


    public static void main(String[] args) throws InterruptedException {

        CounterLimiter counterLimiter = new CounterLimiter();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int m = 0; m < 1; m++) {
            int finalM = m;
            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 105; i++) {
                        counterLimiter.doLimit("localhost" + finalM);
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }

        countDownLatch.await();


    }


}
