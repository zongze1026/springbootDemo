package com.zongze.JavaSE.thread.homework;

/**
 * Create By xzz on 2019/5/14
 */
public class Bee implements Runnable {
    private HoneyPot honeyPot;

    public Bee(HoneyPot honeyPot) {
        this.honeyPot = honeyPot;
    }

    @Override
    public void run() {
        try {
            while (true){
                honeyPot.product();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
