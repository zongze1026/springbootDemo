package com.zongze.bigdata.homework;

/**
 * Create By xzz on 2019/5/14
 */
public class BearTest {


    public static void main(String[] args) {

        HoneyPot honeyPot = new HoneyPot();

        new Thread(new Bear(honeyPot), "熊大").start();
        new Thread(new Bear(honeyPot), "熊二").start();
        for (int i = 0; i < 100; i++) {
            new Thread(new Bee(honeyPot),  + i+"号蜜蜂").start();
        }


    }


}
