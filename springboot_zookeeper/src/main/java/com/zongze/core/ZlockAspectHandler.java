package com.zongze.core;

import com.zongze.annotation.Zlock;
import com.zongze.lock.ReentrantLock;
import org.apache.zookeeper.KeeperException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import java.io.IOException;

/**
 * Create By xzz on 2019/12/23
 */
@Order(0)
@Aspect
public class ZlockAspectHandler {

    private ReentrantLock lock;


    @Around(value = "@annotation(zlock)")
    public Object handler(ProceedingJoinPoint joinPoint, Zlock zlock) throws Throwable {
        lock.lock(zlock.value());
        return joinPoint.proceed();
    }

    @AfterReturning(value = "@annotation(zlock)")
    public void releaseLock(JoinPoint joinPoint, Zlock zlock) throws InterruptedException, IOException, KeeperException {
        lock.unLock();
    }


    @AfterThrowing(value = "@annotation(zlock)")
    public void releaseLockBeforeThrowException(JoinPoint joinPoint, Zlock zlock) throws InterruptedException, IOException, KeeperException {
        lock.unLock();
    }

    public ZlockAspectHandler(ReentrantLock lock) {
        this.lock = lock;
    }
}
