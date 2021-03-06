package com.zongze.core;

import com.zongze.annotation.Zlock;
import com.zongze.lock.ZKReentrantLock;
import org.apache.zookeeper.KeeperException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create By xzz on 2019/12/23
 */
@Order(0)
@Aspect
public class ZKLockAspectHandler {

    private ZKClientFactory zkClientFactory;
    private Map<String, ZKReentrantLock> lockContext = new ConcurrentHashMap<>();


    @Around(value = "@annotation(zlock)")
    public Object handler(ProceedingJoinPoint joinPoint, Zlock zlock) throws Throwable {
        ZKReentrantLock lock = zkClientFactory.getLock();
        lockContext.put(String.valueOf(Thread.currentThread().getId()), lock);
        lock.lock(zlock.value());
        return joinPoint.proceed();
    }

    @AfterReturning(value = "@annotation(zlock)")
    public void releaseLock(JoinPoint joinPoint, Zlock zlock) throws InterruptedException, IOException, KeeperException {
        lockContext.remove(String.valueOf(Thread.currentThread().getId())).unLock();
    }


    @AfterThrowing(value = "@annotation(zlock)")
    public void releaseLockBeforeThrowException(JoinPoint joinPoint, Zlock zlock) throws InterruptedException, IOException, KeeperException {
        lockContext.remove(String.valueOf(Thread.currentThread().getId())).unLock();
    }

    public ZKLockAspectHandler(ZKClientFactory zkClientFactory) {
        this.zkClientFactory = zkClientFactory;
    }
}
