package com.zongze.model;

import lombok.Data;

/**
 * Create By xzz on 2019/12/23
 */
@Data
public class ZlockInfo {

    /**
     * 临时序列节点路径
     * @param:
     * @return:
     */
    private String path;

    /**
     * 节点是否被激活
     * @param:
     * @return:
     */
    private boolean active;


    /**
     * 前一个节点的路径
     * @param:
     * @return:
     */
    private String preNode;

    public ZlockInfo(String path) {
        this.path = path;
    }
}
