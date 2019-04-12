package com.zongze.sender;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By xzz on 2019/4/12
 */
@Service
public class MqSender {


    @Autowired
    private AmqpTemplate amqpTemplate;


    public void sendMessage(){
        MqEntity mqEntity = new MqEntity();
        mqEntity.setContent("测试死信队列的重试次数");
        amqpTemplate.convertAndSend("pub_send_exchange","pub_ttl_key", JSON.toJSONString(mqEntity), message -> {
            message.getMessageProperties().setExpiration(String.valueOf(mqEntity.getTimeOut()));
            return message;
        });
    }



}
