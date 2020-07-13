package com.zongze.rocketmq.produce;

import com.zongze.rocketmq.entity.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2020/7/13
 */
@Component
public class RocketMqSender {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private static final String TOPIC = "rocketmq-test";

    public void sendMsg(Order msg) {
        rocketMQTemplate.convertAndSend(TOPIC, msg);
    }


}
