package com.zongze.lock.Impl;

import com.zongze.model.LockInfo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Create By xzz on 2019/12/13
 */
public class ReentrantLock extends AbstractDistributedLock {

    private RLock lock;

    @Override
    public boolean lock() {
        lock = redissonClient.getLock(lockInfo.getLockName());
        try {
            return lock.tryLock(lockInfo.getWaitTime(), lockInfo.getTimeUnit());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean unlock() {
        if(lock.isHeldByCurrentThread()){
            try {
                return lock.forceUnlockAsync().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            } catch (ExecutionException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public ReentrantLock(LockInfo lockInfo, RedissonClient redissonClient) {
        this.lockInfo = lockInfo;
        this.redissonClient = redissonClient;
    }
}
