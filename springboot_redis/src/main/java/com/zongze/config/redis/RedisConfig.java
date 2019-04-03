package com.zongze.config.redis;

import com.alibaba.fastjson.parser.ParserConfig;
import com.zongze.component.KeyExpireListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.security.Key;

/**
 * Created by xieZZ on 2018/11/11
 */
@Configuration
public class RedisConfig {


    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        JsonObjectSerializer<Object> valueSerializer = new JsonObjectSerializer<>(Object.class);
        redisTemplate.setConnectionFactory(factory);
        /** key-value类型*/
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        /** hash类型*/
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        ParserConfig.getGlobalInstance().addAccept("com.zongze.");
        return redisTemplate;
    }


    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }

    @Bean
    public KeyExpireListener keyExpireListener(RedisMessageListenerContainer container) {
        KeyExpireListener listener = new KeyExpireListener(container);
        return listener;
    }


}
