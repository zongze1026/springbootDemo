package com.zongze.sender.ttl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
public class TTLSender {


    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send() {
        String content = "this is topic test %s";
        System.out.println(String.format(content, "发送成功"));
        amqpTemplate.convertAndSend("sendTTLExchange", "ttl.key", content, message -> {
            message.getMessageProperties().setExpiration("500000");
            return message;
        });
    }


    public void send1() {
        String content = "this is topic test %s";
        System.out.println(String.format(content, "发送成功"));
        amqpTemplate.convertAndSend("sendTTLExchange", "ttl.key", content);
    }


}
