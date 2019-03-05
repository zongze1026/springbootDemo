package com.zongze.test;

/**
 * Create By xzz on 2019/3/5
 */
public abstract class Abstranct<T extends Object> implements Base {

    private T t;

    protected Abstranct(T t) {
        this.t = t;
    }

    @Override
    public T getObject() {
        return t;
    }
}
