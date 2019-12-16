package com.zongze.model;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * Create By xzz on 2019/12/13
 */
@Data
public class LockInfo {

    public String lockName;

    public long waitTime;

    public long leaseTime;

    public TimeUnit timeUnit;

    public LockType lockType;

    public LockInfo(String lockName, long waitTime, long leaseTime, TimeUnit timeUnit, LockType lockType) {
        this.lockName = lockName;
        this.waitTime = waitTime;
        this.leaseTime = leaseTime;
        this.timeUnit = timeUnit;
        this.lockType = lockType;
    }
}
