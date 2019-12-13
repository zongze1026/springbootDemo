package com.zongze.model;

/**
 * Create By xzz on 2019/12/13
 * 锁类型
 */
public enum LockType {
    /**
     * 读锁
     */
    Read,
    /**
     * 写锁
     */
    Write,
    /**
     * 公平锁
     */
    Fair,
    /**
     * 重入锁
     */
    Reentrant;

    LockType() {
    }


}
