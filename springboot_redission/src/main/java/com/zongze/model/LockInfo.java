package com.zongze.model;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * Create By xzz on 2019/12/13
 */
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

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public LockType getLockType() {
        return lockType;
    }

    public void setLockType(LockType lockType) {
        this.lockType = lockType;
    }
}
