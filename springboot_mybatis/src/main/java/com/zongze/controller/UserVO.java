package com.zongze.controller;

/**
 * @Date 2020/8/19 10:12
 * @Created by xzz
 */
public class UserVO {

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "年龄")
    private int age;

    @Excel(name = "资产")
    private double price;

    @Excel(name = "身份表示")
    private Long id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = "修改后的名称";
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

    @Override
    public String toString() {
        return "UserVO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
