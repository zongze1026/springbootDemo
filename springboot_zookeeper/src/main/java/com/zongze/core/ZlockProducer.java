package com.zongze.core;

import com.zongze.lock.ReentrantLock;

import java.io.IOException;

/**
 * Create By xzz on 2020/1/15
 */
public class ZlockProducer {
    
    private ZKClientFactory zkClientFactory;


    public ReentrantLock getLock() throws IOException {
        return new ReentrantLock(zkClientFactory.getZookeeperClient());
    }

    public ZlockProducer(ZKClientFactory zkClientFactory) {
        this.zkClientFactory = zkClientFactory;
    }
}
