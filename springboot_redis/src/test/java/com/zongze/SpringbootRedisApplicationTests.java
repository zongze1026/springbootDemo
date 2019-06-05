package com.zongze;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private JavaMailSender sender;

    @Test
    public void contextLoads() {
    }



    @Test
    public void mailSend(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("13916257992@163.com");
        message.setTo("994711007@qq.com");
        message.setSubject("测试邮件");
        message.setText("邮件测试");
    }





}

