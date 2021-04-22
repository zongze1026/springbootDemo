package com.zongze.config;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

/**
 * Create By xzz on 2018/11/21
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<StringStack> dataSourceHolder = new ThreadLocal<StringStack>() {
        @Override
        protected StringStack initialValue() {
            return new StringStack();
        }
    };


    /**
     * 设置数据源的key
     */
    public static void setKey(String key) {
        System.out.println("===========设置数据源key：" + key + "=================");
        StringStack stack = dataSourceHolder.get();
        stack.pushString(key);
    }

    /**
     * 获取key
     */
    public static String getKey() {
        StringStack stack = dataSourceHolder.get();
        return stack.peekString();
    }

    /**
     * 清除key
     */
    public static void clear() {
        StringStack stack = dataSourceHolder.get();
        stack.popString();
    }


    public static void main(String[] args) {

        StringStack stack = new StringStack();
        stack.pushString("a");
        stack.pushString("b");
        stack.pushString("c");
        System.out.println(stack.peekString());
        System.out.println(stack.popString());
        System.out.println(stack.popString());
        System.out.println(stack.popString());



    }


}
