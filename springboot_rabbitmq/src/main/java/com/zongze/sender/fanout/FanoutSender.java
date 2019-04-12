package com.zongze.sender.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
public class FanoutSender {


    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send(){
        String content = "this is fanout test %s";
        System.out.println(String.format(content,"发送成功"));
        amqpTemplate.convertAndSend("fanoutExchange","",content);
    }



}
