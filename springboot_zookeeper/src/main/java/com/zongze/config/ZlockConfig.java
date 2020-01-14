package com.zongze.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Create By xzz on 2019/12/23
 */
@Data
@ConfigurationProperties(prefix = "spring.zlock")
public class ZlockConfig {
    /**
     * zookeeper的父节点
     * @param:
     * @return:
     */
    private String rootPath;

    /**
     * zookeeper服务器的host
     * @param:
     * @return:
     */
    private String host;

    /** 分隔符
     * @param:
     * @return:
     */
    private final String separate = "/";

    /**
     * session过期时间，默认20秒
     * @param:
     * @return:
     */
    private int sessionTimeout = 2000000000;

}
