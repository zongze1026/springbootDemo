package com.zongze.service;
import com.zongze.annotation.Zlock;
import org.springframework.stereotype.Service;

/**
 * Create By xzz on 2019/12/24
 */
@Service
public class ZlockTestService {



    @Zlock("zlock")
    public void testZlock() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+" is stop");
    }





}
