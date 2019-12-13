package com.zongze.core;

import com.zongze.annotation.Dlock;
import com.zongze.config.RedissonLockConfig;
import com.zongze.lock.Lock;
import com.zongze.model.LockInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create By xzz on 2019/12/13
 */
@Order(0)
@Aspect
@Component
public class DlockAspectHandler {

    private static final Logger logger = LoggerFactory.getLogger(DlockAspectHandler.class);
    private final String PREFIX = "lock:";
    private final ConcurrentHashMap<String, Lock> lockContext = new ConcurrentHashMap<>();

    @Autowired
    private RedissonLockConfig lockConfig;
    @Autowired
    private DistributedLockFactory lockFactory;

    @Around(value = "@annotation(dlock)")
    public Object handler(ProceedingJoinPoint joinPoint, Dlock dlock) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LockInfo lockInfo = new LockInfo(dlock.unit(), dlock.lockType());
        lockInfo.setLockName(getLockName(methodSignature, dlock));
        lockInfo.setWaitTime(getWaitTime(dlock));
        lockInfo.setLeaseTime(getLeaseTime(dlock));
        Lock lock = lockFactory.getLock(lockInfo);
        lockContext.put(lockInfo.getLockName(), lock);
        if (!lock.lock()) {
            //获取锁失败策略
        }
        return joinPoint.proceed();
    }

    @AfterReturning(value = "@annotation(dlock)")
    public void releaseLock(ProceedingJoinPoint joinPoint, Dlock dlock) {
        String lockName = getLockName((MethodSignature) joinPoint.getSignature(), dlock);
        Lock lock = lockContext.remove(lockName);
        if (!Objects.isNull(lock)) {
            lock.unlock();
        }
    }

    @AfterThrowing(value = "@annotation(dlock)")
    public void releaseLockBeforeThrowException(ProceedingJoinPoint joinPoint, Dlock dlock) {
        String lockName = getLockName((MethodSignature) joinPoint.getSignature(), dlock);
        Lock lock = lockContext.remove(lockName);
        if (!Objects.isNull(lock)) {
            lock.unlock();
        }
    }


    private long getWaitTime(Dlock dlock) {
        return dlock.waitTime() == Long.MIN_VALUE ? lockConfig.getWaitTime() : dlock.waitTime();
    }

    private long getLeaseTime(Dlock dlock) {
        return dlock.leaseTime() == Long.MIN_VALUE ? lockConfig.getLeaseTime() : dlock.leaseTime();
    }

    private String getLockName(MethodSignature methodSignature, Dlock dlock) {
        StringBuilder buffer = new StringBuilder(PREFIX);
        if (StringUtils.isEmpty(dlock.name())) {
            buffer.append(methodSignature.getDeclaringTypeName()).append(".").append(methodSignature.getMethod().getName());
            return buffer.toString();
        }
        buffer.append(dlock.name());
        return buffer.toString();
    }


}
