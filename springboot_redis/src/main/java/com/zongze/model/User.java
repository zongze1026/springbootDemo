package com.zongze.model;

import java.io.Serializable;

/**
 * Create By xzz on 2018/12/13
 */
public class User implements Serializable {

    private String userName;

    private Long age;

    public User(String userName, Long age) {
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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
