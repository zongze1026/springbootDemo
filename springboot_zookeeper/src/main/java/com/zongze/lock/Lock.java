package com.zongze.lock;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * Create By xzz on 2019/12/23
 */
public interface Lock {


    /**
     * 分布式锁
     * @param:
     * @return:
     */
    boolean lock(String lockName)throws InterruptedException, IOException, KeeperException;



    /**
     * 锁释放
     * @param:锁的名称
     * @return:
     */
    void unLock()throws InterruptedException, IOException, KeeperException ;




}
