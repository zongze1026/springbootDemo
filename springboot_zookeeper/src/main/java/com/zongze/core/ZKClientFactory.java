package com.zongze.core;

import com.alibaba.fastjson.JSON;
import com.zongze.config.ZlockConfig;
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

    private ZlockConfig zlockConfig;


    /**
     * 获取zookeeper操作客户端
     */
    public ZookeeperClient getZookeeperClient() throws IOException {
        return new ZookeeperClient(getConnect(), zlockConfig);
    }


    /**
     * 获取zookeeper连接
     */
    private ZooKeeper getConnect() throws IOException {
        return new ZooKeeper(zlockConfig.getHost().trim(), zlockConfig.getSessionTimeout(), new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                logger.info("成功连接zookeeper:{}", JSON.toJSONString(watchedEvent));
            }
        });
    }

    public ZKClientFactory(ZlockConfig zlockConfig) {
        this.zlockConfig = zlockConfig;
    }
}
