package com.zongze.receice.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
@RabbitListener(queues = "topic.message2")
public class TopicMessage2Receive {


    @RabbitHandler
    public void receive(String content) {

        System.out.println(TopicMessage2Receive.class.getName() + ":" + content);
    }


}
