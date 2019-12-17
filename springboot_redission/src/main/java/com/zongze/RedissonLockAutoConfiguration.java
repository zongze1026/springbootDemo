package com.zongze;

import com.zongze.core.DlockAspectHandler;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * Create By xzz on 2019/12/12
 */
@Primary
@Configuration
@EnableConfigurationProperties({RedissonLockConfig.class})
@ConditionalOnProperty(prefix = "spring.dlock", name = "enable", havingValue = "true", matchIfMissing = true)
@Import({DlockAspectHandler.class})
public class RedissonLockAutoConfiguration {

    @Autowired
    private RedissonLockConfig redissonConfig;

    @Bean
    @ConditionalOnMissingBean({RedissonClient.class})
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(redissonConfig.getAddress());
        singleServerConfig.setDatabase(redissonConfig.getDatabase());
        singleServerConfig.setPassword(redissonConfig.getPassword());
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

}
