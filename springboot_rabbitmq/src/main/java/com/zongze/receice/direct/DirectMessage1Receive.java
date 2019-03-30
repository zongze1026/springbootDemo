package com.zongze.receice.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Create By xzz on 2019/3/29
 */
@Component
@RabbitListener(queues = "directMessage1")
public class DirectMessage1Receive {


    @RabbitHandler
    public void receive(String content, Channel channel, Message message) throws IOException {
        System.out.println(DirectMessage1Receive.class.getName() + ":" + content);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
