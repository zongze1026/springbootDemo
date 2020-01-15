package com.zongze.service;
import com.zongze.core.ZKClientFactory;
import com.zongze.lock.ZKReentrantLock;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Create By xzz on 2019/12/24
 */
@Service
public class ZlockTestService {

    @Autowired
    private ZKClientFactory zkClientFactory;


    public void testZlock() throws InterruptedException, IOException, KeeperException {
        ZKReentrantLock reentrantLock = zkClientFactory.getLock();
        reentrantLock.lock("ahbcd");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+" is stop");
        reentrantLock.unLock();
    }





}
