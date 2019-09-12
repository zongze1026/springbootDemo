package com.zongze.config.redis;

import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xieZZ on 2018/11/11
 */
//@Configuration
public class RedisSentinelConfig {


    @Value("${spring.redis.sentinel.master}")
    private String master;

    @Value("${spring.redis.sentinel.nodes}")
    private String nodes;

    @Value("${spring.redis.sentinel.password}")
    private String password;


    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        //配置redis的哨兵sentinel
        Set<RedisNode> redisNodeSet = new HashSet<>();
        if (StringUtils.isNotBlank(nodes)) {
            String[] sentinelNodes = nodes.split(",");
            if (sentinelNodes != null && sentinelNodes.length > 0) {
                for (int i = 0; i < sentinelNodes.length; i++) {
                    String node = sentinelNodes[i];
                    String[] split = node.split(":");
                    RedisNode senRedisNode = new RedisNode(split[0], Integer.valueOf(split[1]));
                    redisNodeSet.add(senRedisNode);
                }
            }
            redisSentinelConfiguration.setMaster(master);
            redisSentinelConfiguration.setPassword(password);
        }
        redisSentinelConfiguration.setSentinels(redisNodeSet);
        return redisSentinelConfiguration;
    }


    @Bean
    public JedisConnectionFactory jedisConnectionFactory(RedisSentinelConfiguration redisSentinelConfiguration) {
        JedisConnectionFactory factory = new JedisConnectionFactory(redisSentinelConfiguration);
        return factory;
    }


    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        JsonObjectSerializer<Object> valueSerializer = new JsonObjectSerializer<>(Object.class);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
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
