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
    public MqSender mqSender() {
        MqSender mqSender = new MqSender();
        rabbitTemplate.setConfirmCallback(mqSender);
        rabbitTemplate.setReturnCallback(mqSender);
        mqSender.setAmqpTemplate(rabbitTemplate);
        return mqSender;
    }




}
