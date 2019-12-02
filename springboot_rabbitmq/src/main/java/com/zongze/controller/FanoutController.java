package com.zongze.controller;

import com.zongze.sender.fanout.FanoutSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By xzz on 2019/12/2
 */
@RestController
@RequestMapping("mq")
public class FanoutController {

    @Autowired
    private FanoutSender fanoutSender;

    @GetMapping("fanout/send")
    public void send() {
        fanoutSender.send();
    }


}
