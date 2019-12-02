package com.zongze.receice.fanout;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * Create By xzz on 2019/3/29
 */
@Component
public class FanoutMessage1Receive {


    @RabbitListener(queues = "fanoutMessage1")
    public void receive(String content, Channel channel, Message message) throws IOException {

        System.out.println(FanoutMessage1Receive.class.getName() + ":" + content);
        //当前消息的id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //手动应答ack，第二个参数：true:会把小于当前id的消息全部ack掉 false:只ack当前消息
        channel.basicAck(deliveryTag, false);
    }


}
