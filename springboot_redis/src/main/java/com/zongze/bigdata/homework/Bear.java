package com.zongze.bigdata.homework;

/**
 * Create By xzz on 2019/5/14
 */
public class Bear implements Runnable {
    private HoneyPot honeyPot;

    public Bear(HoneyPot honeyPot) {
        this.honeyPot = honeyPot;
    }

    @Override
    public void run() {
        try {
            while (true){
                honeyPot.eat();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
