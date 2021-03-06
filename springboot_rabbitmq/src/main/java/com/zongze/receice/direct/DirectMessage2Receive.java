package com.zongze.receice.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By xzz on 2019/3/29
 */
@Component
@RabbitListener(queues = "directMessage2")
public class DirectMessage2Receive {


    @RabbitHandler
    public void receive(String content, Channel channel, Message message) throws IOException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(DirectMessage2Receive.class.getName() + ":" + content + format.format(new Date()));
            //当前消息的id
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            //手动应答ack，第二个参数：true:会把小于当前id的消息全部ack掉 false:只ack当前消息
            Thread.sleep(5000);
            channel.basicAck(deliveryTag, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
