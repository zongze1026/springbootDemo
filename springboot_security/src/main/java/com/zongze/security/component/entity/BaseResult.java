package com.zongze.security.component.entity;

/**
 * 基础返回值类
 *
 * @version V1.0
 * @date: 2018年8月3日 下午5:45:31
 * @author: shenhufei
 */
public class BaseResult<T> {

    private String code;
    private String message;
    private int count;
    private T data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
