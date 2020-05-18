package com.zongze.core;

import com.zongze.annotation.Dlock;
import com.zongze.lock.Lock;
import com.zongze.model.LockInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import java.util.Objects;

/**
 * Create By xzz on 2019/12/13
 */
@Order(0)
@Aspect
public class DlockAspectHandler {

    private static final Logger logger = LoggerFactory.getLogger(DlockAspectHandler.class);

    private final ThreadLocal<Lock> lockContext = new InheritableThreadLocal<>();

    @Autowired
    private DistributedLockFactory lockFactory;
    @Autowired
    private DlockInfoProducer dlockInfoProducer;

    @Around(value = "@annotation(dlock)")
    public Object handler(ProceedingJoinPoint joinPoint, Dlock dlock) throws Throwable {
        LockInfo lockInfo = dlockInfoProducer.buildLockInfo(joinPoint, dlock);
        Lock lock = lockFactory.getLock(lockInfo);
        if (!lock.lock()) {
            //获取锁失败策略
            logger.info("当前线程：{}获取所失败", Thread.currentThread().getName());
            return null;
        }
        lockContext.set(lock);
        return joinPoint.proceed();
    }

    @AfterReturning(value = "@annotation(dlock)")
    public void releaseLock(JoinPoint joinPoint, Dlock dlock) {
        Lock lock = lockContext.get();
        if (!Objects.isNull(lock)) {
            lock.unlock();
        }
    }

    @AfterThrowing(value = "@annotation(dlock)")
    public void releaseLockBeforeThrowException(JoinPoint joinPoint, Dlock dlock) {
        Lock lock = lockContext.get();
        if (!Objects.isNull(lock)) {
            lock.unlock();
        }
    }


}
