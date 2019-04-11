package com.zongze.send.ttl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
public class TTLFanoutSender {


    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send() {
        String content = "测试ttl广播形式：%s";
        String data = String.format(content, "发送成功");
        System.out.println(data);
        amqpTemplate.convertAndSend("ttlSendExchange", "ttl_fanout_send", data, message -> {
            message.getMessageProperties().setExpiration("500000");
            return message;
        });
    }



}
