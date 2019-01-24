package com.zongze.design.observer;


/**
 * Create By xzz on 2019/1/24
 * 被观察者接口
 */
public interface ObserverAble {
    //添加观察者
    void addObserver(Observer observer);

    //移除观察者
    void removeObserver(Observer observer);

    //通知观察zhe
    void notifyObserver(String message);
}
