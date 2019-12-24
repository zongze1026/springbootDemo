package com.zongze;

import com.zongze.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {


    @Autowired
    private EmailService emailService;



    /**
     * 测试读写锁互斥
     * @param:
     * @return:
     */
    @Test
    public void testLock() throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                try {
                    emailService.testWrite();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.sleep(1000);
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        emailService.testLock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        countDownLatch.await();

    }


    /**
     * 先获取写锁在获取读锁
     * @param:
     * @return:
     */
    @Test
    public void testWriteRead() throws InterruptedException {
        emailService.write();
    }

    /**
     * 获取写锁重入
     * @param:
     * @return:
     */
    @Test
    public void testWriteReentrant() throws InterruptedException {
        emailService.WriteReentrant();
    }



}

