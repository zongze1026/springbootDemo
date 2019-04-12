package com.zongze.test.ttl;

import com.zongze.sender.ttl.TTLSender;
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
public class TTLTest {


    @Autowired
    private TTLSender ttlSender;


    @Test
    public void send(){
        ttlSender.send();
    }


    @Test
    public void send1(){
        ttlSender.send1();
    }




}
