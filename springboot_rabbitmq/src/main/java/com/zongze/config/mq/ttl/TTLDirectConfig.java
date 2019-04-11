package com.zongze.config.mq.ttl;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

/**
 * Create By xzz on 2019/3/30
 */

@Configuration
public class TTLDirectConfig {

    /**
     * 死信队列
     */
    private String ttlqueue = "ttlqueue";


    /**
     * 死信队列
     */
    private String test1_ttl = "ttl.test1";


    /**
     * 死信消息转发的交换机
     */
    private String ttlExchange = "ttlExchange";


    /**
     * 发送消息到死信队列
     */
    private String sendTTLExchange = "sendTTLExchange";


    @Bean
    public Queue ttlTest1() {
        return new Queue(test1_ttl);
    }


    @Bean
    public Queue ttlQueue() {
        Map<String, Object> args = new HashMap<>();
        /**
         * 绑定死信交换机
         */
        args.put("x-dead-letter-exchange", "ttlExchange");
        /**
         * 路由键
         */
        args.put("x-dead-letter-routing-key", "ttlkey");
        /**
         * 设置消失过期时间(全局消息过期时间)
         * 如果消息本身带有过期时间的话，那么会以最先过期的时间为准
         */
        args.put("x-message-ttl", 30000);
        return new Queue(ttlqueue, true, false, false, args);
    }


    @Bean
    public DirectExchange ttlExchange() {
        return new DirectExchange(ttlExchange);
    }


    @Bean
    public DirectExchange sendTTLExchange() {
        return new DirectExchange(sendTTLExchange);
    }


    @Bean
    public Binding sendExchange() {
        return BindingBuilder.bind(ttlQueue()).to(sendTTLExchange()).with("ttl.key");
    }


    @Bean
    public Binding forwordExchange() {
        return BindingBuilder.bind(ttlTest1()).to(ttlExchange()).with("ttlkey");
    }


}
