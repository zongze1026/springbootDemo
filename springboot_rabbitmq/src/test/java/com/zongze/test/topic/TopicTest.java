package com.zongze.test.topic;

import com.zongze.sender.topic.TopicSender;
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
public class TopicTest {


    @Autowired
    private TopicSender topicSender;


    @Test
    public void send(){
        topicSender.send();
    }


    @Test
    public void send1(){
        topicSender.send1();
    }


    @Test
    public void send2(){
        topicSender.send2();
    }




}
