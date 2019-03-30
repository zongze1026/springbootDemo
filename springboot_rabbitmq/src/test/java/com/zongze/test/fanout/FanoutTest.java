package com.zongze.test.fanout;

import com.zongze.send.fanout.FanoutSender;
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
public class FanoutTest {


    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void fanout() {
        fanoutSender.send();
    }


}
