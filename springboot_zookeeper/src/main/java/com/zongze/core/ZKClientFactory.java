package com.zongze.core;

import com.alibaba.fastjson.JSON;
import com.zongze.config.ZKLockConfig;
import com.zongze.lock.ZKReentrantLock;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * Create By xzz on 2020/1/15
 */
public class ZKClientFactory {

    private static final Logger logger = LoggerFactory.getLogger(ZKClientFactory.class);

    private ZKLockConfig zkLockConfig;

    /**
     * 获取分布式锁
     */
    public ZKReentrantLock getLock() throws IOException {
        return new ZKReentrantLock(getZookeeperClient());
    }

    /**
     * 获取zookeeper操作客户端
     */
    public ZookeeperClient getZookeeperClient() throws IOException {
        return new ZookeeperClient(getConnect(), zkLockConfig);
    }


    /**
     * 获取zookeeper连接
     */
    private ZooKeeper getConnect() throws IOException {
        return new ZooKeeper(zkLockConfig.getHost().trim(), zkLockConfig.getSessionTimeout(), new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                logger.info("成功连接zookeeper:{}", JSON.toJSONString(watchedEvent));
            }
        });
    }

    public ZKClientFactory(ZKLockConfig zkLockConfig) {
        this.zkLockConfig = zkLockConfig;
    }
}
