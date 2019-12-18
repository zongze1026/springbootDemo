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


    public TimeUnit timeUnit;

    public LockType lockType;

    public LockInfo(String lockName, long waitTime, TimeUnit timeUnit, LockType lockType) {
        this.lockName = lockName;
        this.waitTime = waitTime;
        this.timeUnit = timeUnit;
        this.lockType = lockType;
    }
}
