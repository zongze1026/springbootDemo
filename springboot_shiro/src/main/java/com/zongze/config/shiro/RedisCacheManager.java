package com.zongze.config.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * Create By xzz on 2018/12/29
 */
public class RedisCacheManager extends AbstractCacheManager {

    @Override
    protected Cache createCache(String s) throws CacheException {
        return new RedisCache();
    }
}
