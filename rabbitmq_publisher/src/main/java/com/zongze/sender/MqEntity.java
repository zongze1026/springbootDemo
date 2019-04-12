package com.zongze.sender;

import lombok.Getter;
import lombok.Setter;

/**
 * Create By xzz on 2019/4/12
 */
@Setter
@Getter
public class MqEntity {

    private String content;

    //重试次数
    private int count = 1;

    private Integer maxCount = 3;

    //消息过期时间
    private long timeOut = 30000;


}
