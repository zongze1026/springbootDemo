package com.zongze.test.ttl;

import com.zongze.sender.ttl.TTLFanoutSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create By xzz on 2019/3/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TTLFanoutTest {


    @Autowired
    private TTLFanoutSender ttlSender;


    @Test
    public void send() {
        int m = 10;
        for (int i = 0; i < 5; i++) {
            ttlSender.send(String.valueOf(m*1000));
            m += 10;
        }
    }


}
