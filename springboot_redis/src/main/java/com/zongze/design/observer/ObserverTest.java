package com.zongze.design.observer;

/**
 * Create By xzz on 2019/1/24
 */
public class ObserverTest {

    public static void main(String[] args) {
        ObserverAbleImpl observerAble = new ObserverAbleImpl();
        Observer observer = null;
        for (int i = 0; i < 5; i++) {
            observer = new ObserverImpl("observer" + i);
            observerAble.addObserver(observer);
        }
        observerAble.receiveMessage("再过一个星期就过年啦！！");
    }


}
