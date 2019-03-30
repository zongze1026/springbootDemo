package com.zongze.receice.topic;

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
@RabbitListener(queues = "topic.message2")
public class TopicMessage2Receive {



    @RabbitHandler
    public void receive(String content, Channel channel, Message message) throws IOException {

        System.out.println(TopicMessage2Receive.class.getName() + ":" + content);
        //当前消息的id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //手动应答ack，第二个参数：true:会把小于当前id的消息全部ack掉 false:只ack当前消息
        channel.basicAck(deliveryTag, false);
    }


}
