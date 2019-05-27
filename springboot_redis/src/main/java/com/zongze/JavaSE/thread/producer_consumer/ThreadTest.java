package com.zongze.JavaSE.thread.producer_consumer;

/**
 * Create By xzz on 2019/5/14
 */
public class ThreadTest {

    public static void main(String[] args) {
        productConsumer();

    }

    private static void productConsumer() {
        Ticket ticket = new Ticket();
        Producer producer = new Producer(ticket, "生产者1号");
        Consumer consumer = new Consumer(ticket, "消费者1号");
        Consumer consume2 = new Consumer(ticket, "消费者2号");
        new Thread(producer, producer.getName()).start();
        new Thread(consumer, consumer.getName()).start();
        new Thread(consume2, consume2.getName()).start();
    }


}
