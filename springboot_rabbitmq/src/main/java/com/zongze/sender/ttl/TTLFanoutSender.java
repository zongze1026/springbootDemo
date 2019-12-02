package com.zongze.sender.ttl;

import com.zongze.config.mq.MqSender;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
public class TTLFanoutSender {


    @Autowired
    private MqSender mqSender;


    public void send(String time) {
        String content = "过期时间为:%s,当前时间戳为:" + System.currentTimeMillis();
        String data = String.format(content, time);
        System.out.println(data);
        mqSender.send("ttlSendExchange", "ttl_fanout_send", data, time);
    }


}
