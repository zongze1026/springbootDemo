package com.zongze.config.mq.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create By xzz on 2019/3/29
 */
@Configuration
public class TopicConfig {

    /**
     * 队列1名称
     */
    private static String message1 = "topic.message1";


    /**
     * 队列2名称
     */
    private static String message2 = "topic.message2";


    /**
     * 交换机名称
     */
    private static String topicExchange = "topicExchange";


    @Bean
    public Queue topicMessage1() {
        return new Queue(message1,true);
    }


    @Bean
    public Queue topicMessage2() {
        return new Queue(message2,true);
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }



    /**
     * 队列message1绑定到交换机中
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicMessage1()).to(topicExchange()).with("topic.message1");
    }


    /**
     * 队列message2绑定到交换机中
     */
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicMessage2()).to(topicExchange()).with("topic.#");
    }



}
