package com.zongze.test.direct;

import com.zongze.sender.direct.DirectSender;
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
public class DirectTest {


    @Autowired
    private DirectSender directSender;


    @Test
    public void send(){
        for (int i=0;i<1000;i++){
            directSender.send();
        }

    }


    @Test
    public void send1(){
        directSender.send1();
    }


    @Test
    public void send2(){
        directSender.send2();
    }

    @Test
    public void send3(){
        for (int i=0;i<10;i++){
            directSender.send3(i);
        }
    }









}
