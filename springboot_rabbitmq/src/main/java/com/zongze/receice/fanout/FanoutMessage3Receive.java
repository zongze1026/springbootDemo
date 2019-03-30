package com.zongze.receice.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
@RabbitListener(queues = "fanoutMessage3")
public class FanoutMessage3Receive {


    @RabbitHandler
    public void receive(String content) {

        System.out.println(FanoutMessage3Receive.class.getName() + ":" + content);
    }


}
