package com.zongze.receice.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
@RabbitListener(queues = "fanoutMessage1")
public class FanoutMessage1Receive {


    @RabbitHandler
    public void receive(String content) {

        System.out.println(FanoutMessage1Receive.class.getName() + ":" + content);
    }


}
