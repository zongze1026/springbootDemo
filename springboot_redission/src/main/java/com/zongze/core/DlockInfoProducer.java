package com.zongze.core;

import com.zongze.RedissonLockConfig;
import com.zongze.annotation.Dlock;
import com.zongze.model.LockInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Create By xzz on 2019/12/16
 */
@Component
public class DlockInfoProducer {

    @Autowired
    private RedissonLockConfig lockConfig;

    private final String LOCK_NAME_TEMPLATE = "lock:%s";
    private final String CURRENT_ID = "lock:%s:%s";
    private final String SEPARATOR = ".";

    private long getWaitTime(Dlock dlock) {
        return lockConfig.getWaitTime() == Long.MIN_VALUE ? dlock.waitTime() : lockConfig.getWaitTime();
    }


    public String getCurrentId(JoinPoint joinPoint, Dlock dlock) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return String.format(CURRENT_ID, getLockName(methodSignature, dlock), Thread.currentThread().getId());
    }

    private String getLockName(MethodSignature methodSignature, Dlock dlock) {
        if (StringUtils.isEmpty(dlock.name())) {
            return String.format(LOCK_NAME_TEMPLATE, methodSignature.getDeclaringTypeName() + SEPARATOR + methodSignature.getMethod().getName());
        }
        return String.format(LOCK_NAME_TEMPLATE, dlock.name());
    }

    public LockInfo buildLockInfo(ProceedingJoinPoint joinPoint, Dlock dlock) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return new LockInfo(getLockName(methodSignature, dlock), getWaitTime(dlock), dlock.unit(), dlock.lockType());
    }


}
