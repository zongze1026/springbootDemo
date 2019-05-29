package com.zongze.bigdata.producer_consumer;

import lombok.Getter;

/**
 * Create By xzz on 2019/5/14
 */
@Getter
public class Producer implements Runnable {
    private Ticket ticket;
    private String name;
    private static Integer i = 1;

    public Producer(Ticket ticket, String name) {
        this.ticket = ticket;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ticket.addTicket(i++);
//                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
