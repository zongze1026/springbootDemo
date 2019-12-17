package com.zongze.service;

import com.zongze.annotation.Dlock;
import com.zongze.util.EmailUtil;
import com.zongze.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By xzz on 2019/12/11
 */
@Service
public class EmailService {

    @Autowired
    private EmailUtil emailUtil;


    public void sendEmail(String address, String title, String content) {
        emailUtil.sendSimpleMail(address, title, content);
    }

    public void setIfAbsent(String key, String value) {
        boolean b = RedisUtil.setIfAbsent(key, value);
        System.out.println(b);
    }

    @Dlock
    public void testLock() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 抢到了资源锁！");
        Thread.sleep(12000);
        System.out.println(Thread.currentThread().getName() + "执行业务完成");
    }


}
