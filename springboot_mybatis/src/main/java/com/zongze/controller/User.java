package com.zongze.controller;

import java.util.Date;

/**
 * @Date 2020/8/18 10:38
 * @Created by xzz
 */
public class User {

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "年龄")
    private int age;

    @Excel(name = "资产")
    private double price;

    @Excel(name = "身份表示")
    private Long id;

    @Excel(name = "生日")
    private Date birthdays;

    public Date getBirthday() {
        return birthdays;
    }

    public void setBirthday(Date birthday) {
        this.birthdays = birthday;
    }

    public User(String name, int age, double price, Long id) {
        this.name = name;
        this.age = age;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
