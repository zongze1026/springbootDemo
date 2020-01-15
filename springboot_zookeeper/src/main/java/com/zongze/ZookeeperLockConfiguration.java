package com.zongze;

import com.zongze.config.ZKLockConfig;
import com.zongze.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create By xzz on 2019/12/24
 */
@Configuration
@EnableConfigurationProperties({ZKLockConfig.class})
@ConditionalOnProperty(prefix = "spring.zlock", name = {"enable"}, havingValue = "true")
public class ZookeeperLockConfiguration {

    @Autowired
    private ZKLockConfig zkLockConfig;

    @Bean
    public ZKClientFactory zkClientFactory() {
        return new ZKClientFactory(zkLockConfig);
    }


    @Bean
    public ZKLockPathConfigInitRunner zkLockPathConfigInitRunner(ZKClientFactory zkClientFactory, ZKLockConfig zkLockConfig) {
        return new ZKLockPathConfigInitRunner(zkLockConfig, zkClientFactory);
    }

    @Bean
    public ZKLockAspectHandler zkLockAspectHandler(ZKClientFactory zkClientFactory) {
        return new ZKLockAspectHandler(zkClientFactory);
    }


}
