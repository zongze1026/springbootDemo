package com.zongze.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Create By xzz on 2018/11/26
 */
public class User implements Serializable {

    private Long id;

    private String userName;

    private Integer age;

    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
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
