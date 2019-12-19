package com.zongze;

import com.zongze.core.DlockAspectHandler;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * redis单机
     *
     * @param:
     * @return:
     */
    @Bean("redissonClient")
    @ConditionalOnMissingBean({RedissonClient.class})
    public RedissonClient singleServer() {
        Config config = new Config();
        config.useSingleServer().
                setAddress(redissonConfig.getAddress()).
                setDatabase(redissonConfig.getDatabase()).
                setPassword(redissonConfig.getPassword());
        return Redisson.create(config);
    }


    /**
     * 哨兵配置
     * @param:
     * @return:
     */
    @Bean("redissonClient")
    @ConditionalOnMissingBean({RedissonClient.class})
    public RedissonClient redisSentinel() {
        Config config = new Config();
        SentinelServersConfig sentinelConfig = config.useSentinelServers();
        sentinelConfig.setDatabase(redissonConfig.getDatabase());
        sentinelConfig.setMasterName("master");
        sentinelConfig.addSentinelAddress("127.0.0.1:26379", "192.168.91.201:26379");
        return Redisson.create(config);
    }


}
