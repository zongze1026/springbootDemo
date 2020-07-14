package com.zongze.rocketmq.produce;
import com.zongze.rocketmq.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.UUID;


/**
 * Create By xzz on 2020/7/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketMqSenderTest {


    @Autowired
    private RocketMqSimpleSender rocketMqSender;


    @Test
    public void sendSimple(){
        Order order = new Order(1,"商品订单", UUID.randomUUID().toString());
        rocketMqSender.sendSimpleMsg(order);
    }

    @Test
    public void sendTX(){
        Order order = new Order(11,"商品订单", UUID.randomUUID().toString());
        rocketMqSender.sendTransactionMessage(order);
    }



}