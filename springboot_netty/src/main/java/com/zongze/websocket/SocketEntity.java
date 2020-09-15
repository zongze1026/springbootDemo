package com.zongze.websocket;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2020/9/7 11:50
 * @Created by xzz
 */
public class SocketEntity implements Serializable {

    private String msg;

    private int age;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
