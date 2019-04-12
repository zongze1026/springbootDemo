package com.zongze.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By xzz on 2019/4/12
 */
@Configuration
public class MqConfig {


    @SuppressWarnings("all")
    @Bean
    public Queue ttlQueue() {
        Map<String, Object> args = new HashMap<>();
        /**
         * 绑定死信交换机
         */
        args.put("x-dead-letter-exchange", "pub_ttl_exchange");
        /**
         * 路由键
         */
        args.put("x-dead-letter-routing-key", "pub.ttl.key");
        /**
         * 设置消失过期时间(全局消息过期时间)
         * 如果消息本身带有过期时间的话，那么会以最先过期的时间为准
         */
//        args.put("x-message-ttl", 30000);
        //exclusive:表示当前队列只能被创建的连接使用并且连接关闭后队列就会被删除
        return new Queue(MqConstants.TTL_QUEUE, true, false, false, args);
    }


    @Bean
    public Queue work1() {
        return new Queue(MqConstants.WORK_QUEUE1, true);
    }


    @Bean
    public Queue work2() {
        return new Queue(MqConstants.WORK_QUEUE2, true);
    }


    @Bean
    public FanoutExchange ttlExchange() {
        return new FanoutExchange(MqConstants.TTL_EXCHANGE);
    }


    @Bean
    public DirectExchange sendExchange() {
        return new DirectExchange(MqConstants.SEND_EXCHANGE);
    }


    @Bean
    public Binding work1Binding() {
        return BindingBuilder.bind(work1()).to(ttlExchange());
    }

    @Bean
    public Binding work2Binding() {
        return BindingBuilder.bind(work2()).to(ttlExchange());
    }


    @Bean
    public Binding pubTTLBinding() {
        return BindingBuilder.bind(ttlQueue()).to(sendExchange()).with("pub_ttl_key");
    }


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * rabbitTemplate配置
     */
    @Bean
    public MessageListener messageListener() {
        MessageListener messageListener = new MessageListener();
        rabbitTemplate.setReturnCallback(messageListener);
        rabbitTemplate.setConfirmCallback(messageListener);
        return messageListener;
    }


    public static void main(String[] args) {
        System.out.println(get());
    }

    private static int get() {
        int i = 0;
        while (true) {
            System.out.println(i);
            if (i == 10) {
                return i;
            }
            i++;
        }
    }


}
