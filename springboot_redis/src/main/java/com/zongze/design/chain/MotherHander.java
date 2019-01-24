package com.zongze.design.chain;

/**
 * Create By xzz on 2019/1/24
 */
public class MotherHander extends Hander {

    //妈妈最多可以提供300
    private double most = 300.00;

    @Override
    public String doHander(double monery) {

        if (monery > most) {
            if (getHander() != null) {
                return getHander().doHander(monery);
            }
        }
        return "从妈妈哪里获得了" + monery + "元";
    }
}
