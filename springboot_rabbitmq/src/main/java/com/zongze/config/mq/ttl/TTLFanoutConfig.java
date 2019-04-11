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
public class TTLFanoutConfig {

    /**
     * 死信队列
     */
    private String ttl_queue = "ttl_fanout_queue";


    /**
     * 监听死信交换机
     */
    private String ttl_listnenr_queue1 = "listnenr_queue1";


    /**
     * 监听死信交换机
     */
    private String ttl_listnenr_queue2 = "listnenr_queue2";



    /**
     * 死信消息转发的交换机
     */
    private String ttlFanoutExchange = "ttlFanoutExchange";


    /**
     * 发送交换机
     */
    private String ttlSendExchange = "ttlSendExchange";



    @SuppressWarnings("all")
    @Bean
    public Queue ttlQueue() {
        Map<String, Object> args = new HashMap<>();
        /**
         * 绑定死信交换机
         */
        args.put("x-dead-letter-exchange", "ttlFanoutExchange");
        /**
         * 路由键
         */
        args.put("x-dead-letter-routing-key", "ttl_fanout_key");
        /**
         * 设置消失过期时间(全局消息过期时间)
         * 如果消息本身带有过期时间的话，那么会以最先过期的时间为准
         */
        args.put("x-message-ttl", 30000);
        return new Queue(ttl_queue, true, false, false, args);
    }


    @Bean
    public Queue ttl_queue1(){
        return new Queue(ttl_listnenr_queue1,true);
    }


    @Bean
    public Queue ttl_queue2(){
        return new Queue(ttl_listnenr_queue2,true);
    }


    @Bean
    public FanoutExchange ttl_fanout_exchange() {
        return new FanoutExchange(ttlFanoutExchange);
    }


    @Bean
    public DirectExchange ttlSendExchange() {
        return new DirectExchange(ttlSendExchange);
    }


    @Bean
    public Binding ttlSendBinding() {
        return BindingBuilder.bind(ttlQueue()).to(ttlSendExchange()).with("ttl_fanout_send");
    }


    @Bean
    public Binding ttl_fanout_bind() {
        return BindingBuilder.bind(ttl_queue1()).to(ttl_fanout_exchange());
    }


    @Bean
    public Binding ttl_fanout_bind2() {
        return BindingBuilder.bind(ttl_queue2()).to(ttl_fanout_exchange());
    }


}
