package com.zongze.design.decorator;

/**
 * Create By xzz on 2019/1/24
 */
public class Decorator implements Car {

    private Car decorator;

    public Decorator(Car decorator) {
        this.decorator = decorator;
    }

    @Override
    public void run() {
        if(decorator instanceof SuperCar){
            decorator.run();
        }else{
            System.out.println("普通汽车改装前");
            decorator.run();
            System.out.println("普通汽车改装后");
            System.out.println("普通汽车能跑180码");
        }
    }
}
