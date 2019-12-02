package com.zongze.receice.ttl;

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
public class TTLFanoutReceive1 {


    @RabbitListener(queues = "listnenr_queue1")
    public void receive(String content, Channel channel, Message message) throws IOException {
        System.out.println(TTLFanoutReceive1.class.getName() + ":" + content);
        String timeString = content.substring(content.lastIndexOf(":") + 1, content.length()-1);
        System.out.println(timeString);
        long startTime = Long.valueOf(timeString);
        System.out.println((System.currentTimeMillis() - startTime) / 1000);
        //当前消息的id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //手动应答ack，第二个参数：true:会把小于当前id的消息全部ack掉 false:只ack当前消息
        channel.basicAck(deliveryTag, false);
    }


}
