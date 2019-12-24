package com.zongze;
import com.zongze.config.ZlockConfig;
import com.zongze.core.ZlockAspectHandler;
import com.zongze.core.ZookeeperClient;
import com.zongze.lock.ReentrantLock;
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
@ConditionalOnProperty(prefix = "spring.zlock",name = "enable",havingValue = "true",matchIfMissing = true)
public class ZookeeperLockConfiguration {

    @Autowired
    private ZlockConfig zlockConfig;

    @Bean
    public ZookeeperClient zookeeperClient(){
        return new ZookeeperClient(zlockConfig);
    }

    @Bean
    public ReentrantLock reentrantLock(){
        return new ReentrantLock(zookeeperClient());
    }

    @Bean
    public ZlockAspectHandler zlockAspectHandler(){
        return new ZlockAspectHandler(reentrantLock());
    }





}
