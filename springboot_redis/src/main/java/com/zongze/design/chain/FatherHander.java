package com.zongze.design.chain;

/**
 * Create By xzz on 2019/1/24
 */
public class FatherHander extends Hander {

    @Override
    public String doHander(double monery) {
        return "从爸爸哪里获得了" + monery + "元";
    }
}
