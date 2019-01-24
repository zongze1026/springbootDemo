package com.zongze.design.chain;

/**
 * Create By xzz on 2019/1/24
 * 责任链模式：过年了小孩子分别向哥哥、妈妈要零花钱；他们最多能提供的金额100,200
 *责任链设计模式的好处就是可以把请求和处理者之间进行解耦
 */
public abstract class Hander {

     Hander hander;

    public Hander getHander() {
        return hander;
    }

    public void setHander(Hander hander) {
        this.hander = hander;
    }

    public abstract String doHander(double monery);


}
