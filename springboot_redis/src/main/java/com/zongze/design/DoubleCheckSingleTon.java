package com.zongze.design;

/**
 * Create By xzz on 2019/8/1
 */
public class DoubleCheckSingleTon {

    private static volatile DoubleCheckSingleTon instance;

    private DoubleCheckSingleTon() {
    }

    public static DoubleCheckSingleTon getInstance() {
        if (null == instance) {
            synchronized (DoubleCheckSingleTon.class) {
                if (null == instance) {
                    instance = new DoubleCheckSingleTon();
                }
            }
        }
        return instance;
    }


}
