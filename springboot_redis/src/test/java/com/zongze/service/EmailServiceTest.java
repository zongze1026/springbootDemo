package com.zongze.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create By xzz on 2019/12/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        emailService.sendEmail("13916257992@163.com", "邮件测试", "测试邮件发送\t\nfdfadasf");
    }


    @Test
    public void testRedisSetAbsent() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    emailService.setIfAbsent("hellow", Thread.currentThread().getId() + "");
                }
            }.start();
        }
    }


}