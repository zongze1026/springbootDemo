package com.zongze.service;

import com.zongze.annotation.Dlock;
import com.zongze.util.EmailUtil;
import com.zongze.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Create By xzz on 2019/12/11
 */
@Service
public class EmailService {

    @Autowired
    private EmailUtil emailUtil;



    public void sendEmail(String address, String title, String content) {
        emailUtil.sendSimpleMail(address,title,content);
    }

    public void setIfAbsent(String key,String value){
        boolean b = RedisUtil.setIfAbsent(key, value);
        System.out.println(b);
    }

    @Dlock
    public void testLock(){

    }


}
