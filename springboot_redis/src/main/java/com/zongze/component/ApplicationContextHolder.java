package com.zongze.component;

import com.zongze.utils.HttpUtil;
import com.zongze.utils.RedisUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Create By xzz on 2019/3/27
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initSystem();
    }


    public static final <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static final Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }


    /**
     * 初始化系统配置
     */
    private void initSystem(){
        RedisUtil.setRedisTemplate(applicationContext.getBean(RedisTemplate.class));
        HttpUtil.setRestTemplate(applicationContext.getBean(RestTemplate.class));
    }


    private ApplicationContextHolder() {
    }


}
