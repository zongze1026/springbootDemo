package com.zongze.design.observer;

/**
 * Create By xzz on 2019/1/24
 */
public class ObserverImpl implements Observer {

    private String name;

    public ObserverImpl(String name) {
        this.name = name;
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + "收到一条消息:" + message);
    }
}
