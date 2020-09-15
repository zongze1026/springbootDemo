package com.zongze.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Date 2020/8/31 10:34
 * @Created by xzz
 */
@Configuration
public class WebConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Bean("stringRedisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(keySerializer);
        return redisTemplate;
    }


}
