package com.zongze.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String Sender;


    public void sendSimpleMail(String toUser, String title, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(Sender);
            message.setSubject(title);
            message.setText(text);
            message.setTo(toUser);
            mailSender.send(message);
            System.out.println("邮件已发送给" + toUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}