package com.zongze;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Create By xzz on 2019/12/12
 */
@Data
@ConfigurationProperties(prefix = "spring.redisson")
public class RedissonLockConfig {
    public String address;
    public int database;
    public String password;
    public long waitTime;
    public long leaseTime;


}
