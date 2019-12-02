package com.zongze.sender.direct;

import com.zongze.config.mq.MqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/29
 */
@Component
public class DirectSender {


    @Autowired
    private MqSender mqSender;


    public void send() {
        String content = "this is direct test %s";
        System.out.println(String.format(content, "发送成功"));
        mqSender.send("directExchange", "", "direct.message2");
    }


    public void send1() {
        String content = "this is direct test %s";
        System.out.println(String.format(content, "发送成功"));
        mqSender.send("directExchange", "direct.message2", content);
    }


    public void send2() {
        String content = "this is direct test %s";
        System.out.println(String.format(content, "发送成功"));
        mqSender.send("directExchange", "direct.message", content);
    }


}
