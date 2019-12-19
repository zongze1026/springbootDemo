package com.zongze.lock.Impl;

import com.zongze.model.LockInfo;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutionException;

/**
 * Create By xzz on 2019/12/13
 */
public class WriteLock extends AbstractDistributedLock {

    private RReadWriteLock readWriteLock;

    @Override
    public boolean lock() {
        readWriteLock = redissonClient.getReadWriteLock(lockInfo.getLockName());
        try {
            return readWriteLock.writeLock().tryLock(lockInfo.getWaitTime(), lockInfo.getTimeUnit());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void unlock() {
        if (readWriteLock.writeLock().isHeldByCurrentThread()) {
            readWriteLock.writeLock().unlock();
        }
    }

    public WriteLock(LockInfo lockInfo, RedissonClient redissonClient) {
        this.lockInfo = lockInfo;
        this.redissonClient = redissonClient;
    }
}
