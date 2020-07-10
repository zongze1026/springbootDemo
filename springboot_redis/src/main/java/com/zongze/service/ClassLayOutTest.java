package com.zongze.service;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;

/**
 * Create By xzz on 2020/6/28
 */
public class ClassLayOutTest {


    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        int n = 2;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        A o = new A();
        System.out.println(Thread.currentThread().getId() + "=" + ClassLayout.parseInstance(o).toPrintable());
        for (int i = 0; i < n; i++) {
            new Thread() {
                @Override
                public void run() {
                    o.test(0);
                    countDownLatch.countDown();
                }
            }.start();
            Thread.sleep(100);
        }
        countDownLatch.await();




    }

    static class A {
        public synchronized void test(int n) {
            try {
                if (n > 0) {
                    Thread.sleep(n);
                }
                System.out.println(Thread.currentThread().getId() + "=" + ClassLayout.parseInstance(this).toPrintable());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
