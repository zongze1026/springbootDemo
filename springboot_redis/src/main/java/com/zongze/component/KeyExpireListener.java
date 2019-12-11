package com.zongze.component;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Create By xzz on 2019/4/2
 */
public class KeyExpireListener extends KeyExpirationEventMessageListener {
    public KeyExpireListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    /**
     * redis过期回调；message.toString可以获取到key值
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = message.toString();
        System.out.println("过期的key为：" + key);
    }


}
