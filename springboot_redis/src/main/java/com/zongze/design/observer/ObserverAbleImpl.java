package com.zongze.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By xzz on 2019/1/24
 */
public class ObserverAbleImpl implements ObserverAble {

    //观察者集合
    List<Observer> observers = null;

    @Override
    public void addObserver(Observer observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers != null && observers.size() > 0) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver(String message) {
        if (observers != null && observers.size() > 0) {
            for (Observer observer : observers) {
                observer.receive(message);
            }
        }
    }

    //通知被观察者；消息发生变化，然后通过方法内部调用通知观察者
    public void receiveMessage(String message) {
        notifyObserver(message);
    }


}
