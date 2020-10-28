package com.zongze.freemarker.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Date 2020/10/28 11:07
 * @Created by xzz
 */
public class User {

    private String name;

    private int age;

    private Date birthday;

    private List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void add(String msg){
        list.add(msg);
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
