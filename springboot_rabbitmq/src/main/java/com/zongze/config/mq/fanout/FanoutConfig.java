package com.zongze.config.mq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create By xzz on 2019/3/29
 */
@Configuration
public class FanoutConfig {

    /**
     * 队列1名称
     */
    private static String fanoutMessage1 = "fanoutMessage1";


    /**
     * 队列2名称
     */
    private static String fanoutMessage2 = "fanoutMessage2";


    /**
     * 队列3名称
     */
    private static String fanoutMessage3 = "fanoutMessage3";




    /**
     * 交换机名称
     */
    private static String fanoutExchange = "fanoutExchange";


    @Bean
    public Queue message1() {
        return new Queue(fanoutMessage1);
    }


    @Bean
    public Queue message2() {
        return new Queue(fanoutMessage2);
    }


    @Bean
    public Queue message3() {
        return new Queue(fanoutMessage3);
    }


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }



    /**
     * 队列message1绑定到交换机中
     */
    @Bean
    public Binding bindingMessage1() {
        return BindingBuilder.bind(message1()).to(fanoutExchange());
    }


    /**
     * 队列message2绑定到交换机中
     */
    @Bean
    public Binding bindingMessage2() {
        return BindingBuilder.bind(message2()).to(fanoutExchange());
    }



    /**
     * 队列message2绑定到交换机中
     */
    @Bean
    public Binding bindingMessage3() {
        return BindingBuilder.bind(message3()).to(fanoutExchange());
    }


}
