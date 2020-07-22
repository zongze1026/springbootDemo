package com.zongze;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Date 2020/7/20 21:15
 * @Created by xzz
 */
public class CountDownLatchTest {

    private static final int COUNT = 20;

    public static void main(String[] args) throws InterruptedException {

//        countDownLatchTest();
//        return;
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                int total = map.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
                System.out.println(total);
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(10);


        for (int i=0;i<10;i++){
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    map.put(Thread.currentThread().getName(), finalI+1);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }



    }

    private static void countDownLatchTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);

        for (int i = 0; i < COUNT; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    countDownLatch.countDown();
                    countDownLatch.await();

                    System.out.println(finalI);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(5000000);
    }


}
