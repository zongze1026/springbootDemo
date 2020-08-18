package com.zongze.controller;

import java.util.Date;

/**
 * @Date 2020/8/17 17:06
 * @Created by xzz
 */
public class User {

    @Excel(name = "姓名")
    private String name;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "分数")
    private Long scop ;
    @Excel(name = "生日")
    private Date birthday;

    public User(String name, Integer age, Long scop, Date birthday) {
        this.name = name;
        this.age = age;
        this.scop = scop;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getScop() {
        return scop;
    }

    public void setScop(Long scop) {
        this.scop = scop;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
