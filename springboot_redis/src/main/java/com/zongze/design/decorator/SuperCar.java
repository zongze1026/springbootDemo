package com.zongze.design.decorator;

/**
 * Create By xzz on 2019/1/24
 * 装饰者的身份
 */
public class SuperCar implements Car {

    @Override
    public void run() {
        System.out.println("超级跑车能够跑180码");
    }
}
