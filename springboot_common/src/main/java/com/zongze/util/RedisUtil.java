package com.zongze.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Create By xzz on 2018/12/7
 */
public class RedisUtil {

    private static RedisTemplate redisTemplate;

    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /**
     * 存入key、value
     * 有时间则设定过期时间
     */
    public static void set(Object key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过key查询值
     */
    public static Object get(Object key) {
        try {
            return key == null ? null : redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置过期时间
     */
    public static void expire(Object key, long time) {
        try {
            if (key != null && key != "" && time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 是否存在key
     */
    public static boolean hasKey(Object key) {
        try {
            if (key != null && key != "") {
                return redisTemplate.hasKey(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 通过key删除value
     */
    public static boolean del(Object key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * redis自增
     */
    public static long increment(String key, int count) {
        return redisTemplate.opsForValue().increment(key, count);
    }
}
