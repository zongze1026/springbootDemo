package com.zongze.config.redis;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by xieZZ on 2018/11/11
 */
//@Configuration
public class RedisConfig {



    @Bean
    @Primary
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){

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


}
