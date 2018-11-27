package com.zongze.config;



/**
 * Create By xzz on 2018/11/21
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();


    /**
     * 设置数据源的key
     */
    public static void setKey(String key) {
        System.out.println("===========设置数据源key："+key+"=================");
        dataSourceHolder.set(key);
    }

    /**
     * 获取key
     */
    public static String getKey() {
        return dataSourceHolder.get();
    }

    /**
     * 清除key
     */
    public static void clear() {
        dataSourceHolder.remove();
    }



}
