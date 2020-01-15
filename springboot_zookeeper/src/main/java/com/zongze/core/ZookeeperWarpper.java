package com.zongze.core;

import lombok.Data;
import org.apache.zookeeper.ZooKeeper;

/**
 * Create By xzz on 2020/1/15
 * 封装zookeeper客户端，增加session过期判断
 */
@Data
public class ZookeeperWarpper {

    /**
     * zookeeper客户端
     */
    private ZooKeeper connect;


    /**
     * session过期时间
     */
    private long sessionTimeOut;

    public ZookeeperWarpper(ZooKeeper connect, long sessionTimeOut) {
        this.connect = connect;
        this.sessionTimeOut = sessionTimeOut;
    }
}
