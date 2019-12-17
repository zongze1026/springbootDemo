package com.zongze;

import com.zongze.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private EmailService emailService;


    @Test
    public void mailSend() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("13916257992@163.com");
        message.setTo("994711007@qq.com");
        message.setSubject("测试邮件");
        message.setText("邮件测试");
        sender.send(message);
    }


    @Test
    public void testLock() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 2; i++) {
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


}

