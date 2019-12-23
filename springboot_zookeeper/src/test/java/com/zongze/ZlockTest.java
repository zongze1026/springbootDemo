package com.zongze;

import com.zongze.annotation.Zlock;
import com.zongze.service.TestZlock;
import org.apache.zookeeper.KeeperException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Create By xzz on 2019/12/23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ZlockTest {

    @Autowired
    private TestZlock testZlock;


    @Test
    public void testZlock() throws InterruptedException, IOException, KeeperException {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        testZlock.zlockTest();
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        countDownLatch.await();
    }




}
