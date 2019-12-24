package com.zongze.service;

import com.zongze.annotation.Dlock;
import com.zongze.model.LockType;
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


    @Dlock(lockType = LockType.Read, name = "com.zongze.service.EmailService")
    public void testLock() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 抢到了资源锁！" + " 正在读数据");
        Thread.sleep(1000);
    }


    @Dlock(lockType = LockType.Write, name = "com.zongze.service.EmailService")
    public void testWrite() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 抢到了资源锁！" + " 正在写数据====================");
        Thread.sleep(5000);
        System.out.println("写线程已经处理完");
    }


    @Dlock(lockType = LockType.Write, name = "com.zongze.service.EmailService")
    public void write() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 抢到了资源锁！" + " 正在写数据===========");
        Thread.sleep(1000);
        System.out.println("写线程已经处理完");
        read();
    }

    @Dlock(lockType = LockType.Read, name = "com.zongze.service.EmailService")
    public void read() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 抢到了资源锁！" + " 正在读数据====================");
        Thread.sleep(1000);
        System.out.println("线程处理完读数据");
    }

    @Dlock(lockType = LockType.Write, name = "com.zongze.service.EmailService")
    public void WriteReentrant() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 抢到了资源锁！" + " 正在处理第一个写方法");
        Thread.sleep(1000);
        write();
    }
}
