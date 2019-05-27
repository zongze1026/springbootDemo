package com.zongze.JavaSE.thread.homework;

/**
 * Create By xzz on 2019/5/14
 */
public class HoneyPot {

    private  Integer MAX = 20;
    private  Integer SINGLE = 0;


    public void product() {
        try {
            synchronized (this) {
                if (SINGLE < MAX) {
                    SINGLE++;
                    System.out.println(Thread.currentThread().getName() + "蜜蜂生产了1克蜂蜜,现在一共" + SINGLE + "克蜂蜜！");
                    notifyAll();
                } else {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void eat() {
        try {
            synchronized (this) {
                if (SINGLE == MAX) {
                    System.out.println(Thread.currentThread().getName() + "吃掉了克" + SINGLE + "蜂蜜");
                    SINGLE = 0;
                    notifyAll();
                } else {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
