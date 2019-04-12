package com.zongze.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create By xzz on 2019/4/12
 */
@Configuration
public class MqConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Bean
    public RabbitSender rabbitSender() {
        RabbitSender rabbitSender = new RabbitSender(rabbitTemplate);
        rabbitTemplate.setReturnCallback(rabbitSender);
        rabbitTemplate.setConfirmCallback(rabbitSender);
        return rabbitSender;
    }


}
