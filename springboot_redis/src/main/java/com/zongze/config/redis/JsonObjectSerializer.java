package com.zongze.config.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * Created by xieZZ on 2018/11/11
 */
public class JsonObjectSerializer<T> implements RedisSerializer<T> {
    private static final Charset DEFAULT_ENCODE = Charset.forName("utf-8");
    private static byte[] EMPTY_ARRAY = new byte[0];
    private Class<T> clazz;

    public JsonObjectSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) {
            return EMPTY_ARRAY;
        }
        String jsonString = JSON.toJSONString(o, SerializerFeature.WriteClassName);
        return jsonString.getBytes(DEFAULT_ENCODE);
    }


    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        String data = new String(bytes, DEFAULT_ENCODE);
        return JSON.parseObject(data, clazz);
    }
}
