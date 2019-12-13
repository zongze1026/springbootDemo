package com.zongze.core;
import com.zongze.lock.Impl.FairLock;
import com.zongze.lock.Impl.ReadLock;
import com.zongze.lock.Impl.ReentrantLock;
import com.zongze.lock.Impl.WriteLock;
import com.zongze.lock.Lock;
import com.zongze.model.LockInfo;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Create By xzz on 2019/12/13
 * 根据锁的类型创建锁
 */
@Component
public class DistributedLockFactory {

    @Autowired
    private RedissonClient redissonClient;

    public Lock getLock(LockInfo lockInfo) {
        switch (lockInfo.getLockType()) {
            case Reentrant:
                return new ReentrantLock(lockInfo,redissonClient);
            case Fair:
                return new FairLock(lockInfo,redissonClient);
            case Read:
                return new ReadLock(lockInfo,redissonClient);
            case Write:
                return new WriteLock(lockInfo,redissonClient);
        }
        return null;
    }


}
