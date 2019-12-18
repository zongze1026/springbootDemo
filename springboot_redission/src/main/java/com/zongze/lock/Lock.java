package com.zongze.lock;

/**
 * Create By xzz on 2019/12/13
 * 定义锁类
 */
public interface Lock {


    /**
     * 成功获取锁则返回true
     * @param:
     * @return:
     */
    boolean lock();


    /**
     * 成功释放锁则返回true
     * @param:
     * @return:
     */
    boolean unlock();





}
