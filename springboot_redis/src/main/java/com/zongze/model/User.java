package com.zongze.model;

import java.io.Serializable;

/**
 * Create By xzz on 2018/12/13
 */
public class User implements Serializable {

    private String userName;

    private Integer age;

    public User(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public User() {}


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
