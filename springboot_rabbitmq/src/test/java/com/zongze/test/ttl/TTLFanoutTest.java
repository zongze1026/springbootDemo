package com.zongze.test.ttl;

import com.zongze.send.ttl.TTLFanoutSender;
import com.zongze.send.ttl.TTLSender;
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
    public void send(){
        ttlSender.send();
    }






}
