package com.zongze.service;

import com.zongze.core.ZKClientFactory;
import org.apache.zookeeper.KeeperException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Create By xzz on 2019/12/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class KlockTest {


    @Autowired
    private ZlockTestService zlockTestService;


    @Test
    public void testZlock() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        zlockTestService.testZlock();
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        countDownLatch.await();

    }


}


