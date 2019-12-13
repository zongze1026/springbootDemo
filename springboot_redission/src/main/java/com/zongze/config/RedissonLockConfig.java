package com.zongze.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/12/12
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redisson")
public class RedissonLockConfig {
    public String address;
    public int database;
    public String password;
    public long waitTime;
    public long leaseTime;


}
