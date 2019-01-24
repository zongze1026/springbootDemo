package com.zongze.design.decorator;

/**
 * Create By xzz on 2019/1/24
 * 普通汽车是被装饰者的身份
 */
public class CommonCar implements Car {

    @Override
    public void run() {
        System.out.println("普通汽车只能跑80码");
    }
}
