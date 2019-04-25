package com.zongze.test;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Proxy;

/**
 * Create By xzz on 2019/4/24
 */
public class People implements AbstractPeople {
    @Override
    public void eat() {
        System.out.println("睡觉前吃饭！");
    }

    @Override
    public void sleep() {
        System.out.println("吃完饭睡觉");
    }


    @Override
    public void fly() {
        System.out.println("变身超人--------呼呼呼的飞!");
    }

    public static void main(String[] args) {
        People people = new People();
        System.out.println(JSON.toJSONString(people.getClass().getInterfaces()));
        WorkHandler workHandler = new WorkHandler(people);
        AbstractPeople proxy = (AbstractPeople) Proxy.newProxyInstance(people.getClass().getClassLoader(), people.getClass().getInterfaces(), workHandler);
        System.out.println(proxy.getClass().getName());
        proxy.eat();
    }


}
