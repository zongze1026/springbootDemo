package com.zongze.config.mq.direct;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create By xzz on 2019/3/30
 */

@Configuration
public class DirectConfig {

    /**
     * direct队列1名称
     */
    private String directMessage1 = "directMessage1";

    /**
     * direct队列1名称
     */
    private String directMessage2 = "directMessage2";


    /**
     * direct exchange名称
     */
    private String directExchange = "directExchange";


    @Bean
    public Queue directMessage1() {
        return new Queue(directMessage1,true);
    }


    @Bean
    public Queue directMessage2() {
        return new Queue(directMessage2,true);
    }


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }


    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directMessage1()).to(directExchange()).with("direct.message1");
    }


    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directMessage2()).to(directExchange()).with("direct.message2");
    }


    @Bean
    public Binding directBinding3() {
        return BindingBuilder.bind(directMessage2()).to(directExchange()).with("direct.message1");
    }


}
