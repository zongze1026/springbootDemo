package com.zongze.bigdata.producer_consumer;

import lombok.Getter;

/**
 * Create By xzz on 2019/5/14
 */
@Getter
public class Consumer implements Runnable {
    private Ticket ticket;
    private String name;

    public Consumer(Ticket ticket, String name) {
        this.ticket = ticket;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ticket.getTicket();
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
