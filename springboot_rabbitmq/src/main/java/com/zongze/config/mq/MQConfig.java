package com.zongze.config.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create By xzz on 2019/4/11
 */
@Configuration
public class MQConfig {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Bean
    public MessageListener messageListener() {
        MessageListener messageListener = new MessageListener();
        rabbitTemplate.setConfirmCallback(messageListener);
        rabbitTemplate.setReturnCallback(messageListener);
        return messageListener;
    }





}
