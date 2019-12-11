package com.zongze.service;

import com.zongze.util.EmailUtil;
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
        emailUtil.sendSimpleMail(address,title,content);
    }


}
