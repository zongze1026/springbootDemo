package com.zongze.lock.Impl;

import com.zongze.model.LockInfo;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import java.util.concurrent.ExecutionException;

/**
 * Create By xzz on 2019/12/13
 */
public class ReadLock extends AbstractDistributedLock {

    private RReadWriteLock readWriteLock;

    @Override
    public boolean lock() {
        readWriteLock = redissonClient.getReadWriteLock(lockInfo.getLockName());
        try {
            return readWriteLock.readLock().tryLock(lockInfo.getWaitTime(), lockInfo.getTimeUnit());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean unlock() {
        if (readWriteLock.readLock().isHeldByCurrentThread()) {
            try {
                return readWriteLock.readLock().forceUnlockAsync().get();
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

    public ReadLock(LockInfo lockInfo, RedissonClient redissonClient) {
        this.lockInfo = lockInfo;
        this.redissonClient = redissonClient;
    }

}
