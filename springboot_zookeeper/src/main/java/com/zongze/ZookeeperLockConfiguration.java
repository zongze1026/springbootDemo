package com.zongze;

import com.zongze.config.ZlockConfig;
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
@EnableConfigurationProperties({ZlockConfig.class})
@ConditionalOnProperty(prefix = "spring.zlock", name = {"enable"}, havingValue = "true")
public class ZookeeperLockConfiguration {

    @Autowired
    private ZlockConfig zlockConfig;

    @Bean
    public ZKClientFactory zkClientFactory() {
        return new ZKClientFactory(zlockConfig);
    }


    @Bean
    public ZlockPathConfigInitRunner zlockPathConfigInitRunner(ZKClientFactory zkClientFactory, ZlockConfig zlockConfig) {
        return new ZlockPathConfigInitRunner(zlockConfig, zkClientFactory);
    }

    @Bean
    public ZlockAspectHandler zlockAspectHandler(ZKClientFactory zkClientFactory) {
        return new ZlockAspectHandler(zkClientFactory);
    }


}
