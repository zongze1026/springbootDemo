package com.zongze.rocketmq.produce;

import com.zongze.rocketmq.entity.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import java.util.UUID;
import static com.zongze.rocketmq.entity.Constant.DESTINATION;
import static com.zongze.rocketmq.entity.Constant.*;

/**
 * Create By xzz on 2020/7/13
 */
@Component
public class RocketMqSimpleSender {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送简单消息
     * @param msg
     * @return void
     */
    public void sendSimpleMsg(Order msg) {
        rocketMQTemplate.convertAndSend(DESTINATION, msg);
    }



    /**
     * 发送事务消息
     * @param msg
     * @return void
     */
    public void sendTransactionMessage(Order msg) {
        Message<Order> message = MessageBuilder.withPayload(msg)
                .setHeader("transactionId", UUID.randomUUID().toString().replaceAll(" ", "_"))
                .setHeader("desc", "这是一个事务消息")
                .build();
        rocketMQTemplate.sendMessageInTransaction(ORDER_GROUP,TX_DESTINATION,message,msg);
    }




}
