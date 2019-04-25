package com.zongze.test;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Create By xzz on 2019/4/24
 */
public class WorkHandler implements InvocationHandler {

    private Object targer;

    public WorkHandler(Object targer) {
        this.targer = targer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        System.out.println(method.getName());
        System.out.println(JSON.toJSONString(args));
        Object invoke = method.invoke(targer, args);
        System.out.println(JSON.toJSONString(invoke));
        System.out.println(JSON.toJSONString(proxy.getClass().getInterfaces()));
        return invoke;
    }
}
