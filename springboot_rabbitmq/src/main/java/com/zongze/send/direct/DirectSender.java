package com.zongze.send.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
public class DirectSender {


    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send() {
        String content = "this is direct test %s";
        System.out.println(String.format(content, "发送成功"));
        amqpTemplate.convertAndSend("directExchange", "direct.message1", content);
    }


    public void send1() {
        String content = "this is direct test %s";
        System.out.println(String.format(content, "发送成功"));
        amqpTemplate.convertAndSend("directExchange", "direct.message2", content);
    }


    public void send2() {
        String content = "this is direct test %s";
        System.out.println(String.format(content, "发送成功"));
        amqpTemplate.convertAndSend("directExchange", "direct.message", content);
    }


}
