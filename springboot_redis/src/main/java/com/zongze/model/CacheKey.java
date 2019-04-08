package com.zongze.model;

import com.alibaba.fastjson.JSON;

/**
 * Create By xzz on 2019/3/27
 */
public enum CacheKey {

    QR_EXPIRE("qr:expire:%s", 3 * 60),
    SCKILL_PRODUCT("sck:product:%s", 0),
    QR_PERSISTENCE("qr:pers:%s", 0);


    private String key;
    private Integer cacheTime;

    public String getKey() {
        return key;
    }

    public Integer getCacheTime() {
        return cacheTime;
    }

    CacheKey(String key, Integer expire) {
        this.key = key;
        this.cacheTime = expire;
    }

    public String getKey(Object key) {
        if (!(key instanceof String)) {
            key = JSON.toJSONString(key);
        }
        return String.format(this.getKey(), key);
    }


}
