package com.zongze.design.decorator;

/**
 * Create By xzz on 2019/1/24
 */
public class DecoratorTest {

    public static void main(String[] args) {
        CommonCar commonCar = new CommonCar();
        commonCar.run();
        Decorator decorator = new Decorator(commonCar);
        decorator.run();

        SuperCar superCar  = new SuperCar();
        superCar.run();
        Decorator decoratedCar = new Decorator(superCar);
        decoratedCar.run();

    }


}
