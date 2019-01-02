package com.zongze.config.shiro;
import com.zongze.util.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import java.util.Collection;
import java.util.Set;

/**
 * Create By xzz on 2018/12/29
 */
public class RedisCache<K,V> implements Cache<K,V> {

    @Override
    public V get(K k) throws CacheException {
        return (V)RedisUtil.get(k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        RedisUtil.set(k,v,null);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
       V v =(V) RedisUtil.get(k);
       RedisUtil.del(k);
       return v;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return RedisUtil.getTempelte().getConnectionFactory().getConnection().dbSize().intValue();
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
