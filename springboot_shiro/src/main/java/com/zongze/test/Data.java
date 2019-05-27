package com.zongze.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Create By xzz on 2019/5/23
 */
public class Data {

    private int count;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    protected void set(int count) {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "准备设置数据！" + count);
            this.count = count;
            Thread.sleep(1500);
            System.out.println(Thread.currentThread().getName() + "数据读设置！" + count);
        } catch (InterruptedException e) {
        } finally {
            lock.writeLock().unlock();
        }
    }


    protected void get(int count1) {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "========准备读取数据！" + count1);
            this.count = count1;
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "=======数据读取完成" + count);
        } catch (InterruptedException e) {
        } finally {
            lock.readLock().unlock();
        }
    }


}
