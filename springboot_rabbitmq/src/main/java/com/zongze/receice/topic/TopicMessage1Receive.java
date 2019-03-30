package com.zongze.receice.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
@RabbitListener(queues = "topic.message1")
public class TopicMessage1Receive {


    @RabbitHandler
    public void receive(String content) {

        System.out.println(TopicMessage1Receive.class.getName() + ":" + content);
    }


}
