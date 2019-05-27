package com.zongze.test;

import com.zongze.entity.User;

import java.util.concurrent.Callable;

/**
 * Create By xzz on 2019/5/23
 */
public class CallAbleImpl implements Callable<User> {

    @Override
    public User call() throws Exception {
        User user = new User();
        user.setAge(10);
        user.setUserName("callable");
        Thread.currentThread().sleep(1000);
        return user;
    }
}
