package com.zongze.rocketmq.consumer;
import com.alibaba.fastjson.JSON;
import com.zongze.rocketmq.entity.Order;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2020/7/13
 */
@Component
@RocketMQMessageListener(topic = "rocketmq-test",consumerGroup = "myconsumer")
public class RocketMqListener implements RocketMQListener<Order> {
    private static final String TOPIC = "rocketmq-test";
    @Override
    public void onMessage(Order order) {
        System.out.println(JSON.toJSONString(order));
    }
}
