package com.zongze.lock.Impl;
import com.zongze.lock.Lock;
import com.zongze.model.LockInfo;
import lombok.Data;
import org.redisson.api.RedissonClient;

/**
 * Create By xzz on 2019/12/13
 */
@Data
public abstract class AbstractDistributedLock implements Lock{

    public LockInfo lockInfo;

    public RedissonClient redissonClient;




}
