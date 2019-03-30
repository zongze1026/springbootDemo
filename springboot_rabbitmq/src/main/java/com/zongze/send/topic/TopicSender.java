package com.zongze.send.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
public class TopicSender {


    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send(){
        String content = "this is topic test %s";
        System.out.println(String.format(content,"发送成功"));
        amqpTemplate.convertAndSend("topicExchange","topic.message1",content);
    }


    public void send1(){
        String content = "this is topic test %s";
        System.out.println(String.format(content,"发送成功"));
        amqpTemplate.convertAndSend("topicExchange","topi.aa",content);
    }

    public void send2(){
        String content = "this is topic test %s";
        System.out.println(String.format(content,"发送成功"));
        amqpTemplate.convertAndSend("topicExchange","topic.aa",content);
    }





}
