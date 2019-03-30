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
@RabbitListener(queues = "directMessage2")
public class DirectMessage2Receive {


    @RabbitHandler
    public void receive(String content, Channel channel, Message message) throws IOException {
        System.out.println(DirectMessage2Receive.class.getName() + ":" + content);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
