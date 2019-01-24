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
            System.out.println("超级跑车只能跑80码");
        }else{
            System.out.println("普通汽车能跑180码");
        }
    }
}
