package com.zongze.utils;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.Collection;
import java.util.Set;
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
    public static void set(String key, Object value, long time) {
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
    public static Object get(String key) {
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
    public static void expire(String key, long time) {
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
    public static boolean hasKey(String key) {
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
    public static boolean del(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 自增key
     */
    public static long increment(String key, long count) {
        try {
            return redisTemplate.opsForValue().increment(key, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 存储list类型
     * 从左边存储
     */
    public static long leftPush(String key, Object value) {
        try {
            return redisTemplate.opsForList().leftPush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 存储list类型
     * 从左边存储集合
     */
    public static long leftPushAll(String key, Collection collection) {
        try {
            return redisTemplate.opsForList().leftPushAll(key, collection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 存储list类型
     * 从右边存储
     */
    public static long rightPush(String key, Object value) {
        try {
            return redisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 从列表右边弹出一个元素
     */
    public static <T> T rightPop(String key, Class<T> clazz) {
        try {
            Object o = redisTemplate.opsForList().rightPop(key);
            return (T) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从列表左边弹出一个元素
     */
    public static <T> T leftPop(String key, Class<T> clazz) {
        try {
            Object o = redisTemplate.opsForList().leftPop(key);
            return (T) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取所有key
     */
    public static Set<String> keys(String pattern) {
        try {
            return redisTemplate.keys(pattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
