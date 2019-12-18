package com.zongze.lock.Impl;
import com.zongze.model.LockInfo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import java.util.concurrent.ExecutionException;

/**
 * Create By xzz on 2019/12/13
 */
public class FairLock extends AbstractDistributedLock {

    private RLock fairLock;

    @Override
    public boolean lock() {
        fairLock = redissonClient.getFairLock(lockInfo.getLockName());
        try {
            return fairLock.tryLock(lockInfo.getWaitTime(), lockInfo.getTimeUnit());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean unlock() {
        if (fairLock.isHeldByCurrentThread()) {
            try {
                return fairLock.forceUnlockAsync().get();
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

    public FairLock(LockInfo lockInfo, RedissonClient redissonClient) {
        this.lockInfo = lockInfo;
        this.redissonClient = redissonClient;
    }

}
