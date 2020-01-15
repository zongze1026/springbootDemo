package com.zongze.core;

import com.zongze.config.ZlockConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

/**
 * Create By xzz on 2020/1/15
 * 用于初始化zookeeper锁的根目录
 */
public class ZlockPathConfigInitRunner implements CommandLineRunner {

    private ZlockConfig zlockConfig;

    private ZKClientFactory zkClientFactory;


    @Override
    public void run(String... args) throws Exception {
        ZookeeperClient zkClient = zkClientFactory.getZookeeperClient();
        String separate = zlockConfig.getSeparate();
        StringBuilder buffer = new StringBuilder();
        Arrays.asList(zlockConfig.getRootPath().split(separate)).forEach(path -> {
            if (StringUtils.isNotBlank(path)) {
                buffer.append(separate).append(path);
                try {
                    if (!zkClient.existNode(buffer.toString())) {
                        zkClient.createNode(buffer.toString(), CreateMode.PERSISTENT);
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        zlockConfig.setRootPath(buffer.toString());

    }

    public ZlockPathConfigInitRunner(ZlockConfig config, ZKClientFactory zkClientFactory) {
        this.zlockConfig = config;
        this.zkClientFactory = zkClientFactory;
    }
}
