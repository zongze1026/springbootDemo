package com.zongze;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootRedissionApplicationTests {


    @Autowired
    private RedissonClient redissonClient;


    @Test
    public void test() throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("redission");
                    try {
                        if (readWriteLock.writeLock().tryLock(1, 10, TimeUnit.DAYS)) {
                            System.out.println(Thread.currentThread().getName() + "aquree lock");
                        } else {
                            System.out.println(Thread.currentThread().getName() + "放弃了获取锁");
                        }
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        readWriteLock.writeLock().unlock();
                    }
                }
            }.start();
        }
        countDownLatch.await();
    }


}
