package com.zongze.mongo.util;

import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Date 2020/12/25 10:17
 * @Created by xiezz
 */
public class ApplicationContextHolder {


    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextHolder.applicationContext = applicationContext;
        init();
    }

    private static void init() {
        MongodbUtil.setMongoTemplate(getBean(MongoTemplate.class));
    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);

    }

}
