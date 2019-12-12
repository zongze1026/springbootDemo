package com.zongze.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/3/27
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initSystemBean();
    }

    /**
     * 初始化系统
     * @param:
     * @return:
     */
    private void initSystemBean() {

    }


    public static final <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static final Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }


    private ApplicationContextHolder() {
    }


}
