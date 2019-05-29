package com.zongze.bigdata.producer_consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By xzz on 2019/5/14
 */
public class Ticket {

    private List<Integer> tickets = new ArrayList<>();

    private Integer MAX = 1;
    private Object lock = new Object();


    public void addTicket(Integer no) throws InterruptedException {
        synchronized (lock) {
            while (tickets.size() >= MAX) {
                lock.wait();
            }
            tickets.add(no);
            lock.notifyAll();
            System.out.println(Thread.currentThread().getName() + "加入一个号：" + no + "======总票数" + tickets.size());
        }
    }


    public Integer getTicket() throws InterruptedException {
        synchronized (lock) {
            if (!tickets.isEmpty()) {
                Integer num = tickets.remove(0);
                System.out.println(Thread.currentThread().getName() + "取走一个号：" + num + "==========总票数" + tickets.size());
                lock.notifyAll();
                return num;
            } else {
                lock.wait();
            }
        }
        return null;
    }


}
