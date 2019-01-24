package com.zongze.design.chain;

/**
 * Create By xzz on 2019/1/24
 */
public class BrotherHander extends Hander {

    private double most = 100.00;

    @Override
    public String doHander(double monery) {
        if (monery > most) {
            return getHander().doHander(monery);
        }
        return "从哥哥哪里获得了" + monery + "元";
    }
}
