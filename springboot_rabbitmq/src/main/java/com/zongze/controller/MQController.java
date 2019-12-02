package com.zongze.controller;

import com.zongze.sender.fanout.FanoutSender;
import com.zongze.sender.ttl.TTLFanoutSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By xzz on 2019/12/2
 */
@RestController
@RequestMapping("mq")
public class MQController {

    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private TTLFanoutSender ttlFanoutSender;

    @GetMapping("fanout/send")
    public void send() {
        fanoutSender.send();
    }

    @GetMapping("ttl/send")
    public void ttlSend() {
        int m = 50;
        for (int i = 0; i < 10; i++) {
            ttlFanoutSender.send(String.valueOf(m*1000));
            m-=5;
        }
    }


}
