package com.zongze.entity;

import java.io.Serializable;

/**
 * Create By xzz on 2018/11/26
 */
public class User implements Serializable {

    private Long id;

    private String userName;

    private String passWord;

    private Integer age;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
