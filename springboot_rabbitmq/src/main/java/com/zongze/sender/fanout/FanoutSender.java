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


    public void send() {
        for (int i = 1; i <= 10; i++) {
            String content = "this is fanout test %s " + i;
            amqpTemplate.convertAndSend("fanoutExchange", "", content);
        }
    }


}
