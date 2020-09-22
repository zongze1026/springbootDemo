package com.zongze.controller;


import java.util.Date;

/**
 * @Date 2020/9/21 20:12
 * @Created by xzz
 */
public class UserImport {


    @Excel(name = "名称")
    private String name;

    @Excel(name = "年龄")
    private Integer age;

    @Excel(name = "生日")
    private Date birthday;

    @Excel(name = "性别")
    private Float sex;

    @Excel(name = "是否毕业")
    private Boolean aa;

    @Excel(name = "存款")
    private Double monery;

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


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getMonery() {
        return monery;
    }

    public void setMonery(Double monery) {
        this.monery = monery;
    }

    public Float getSex() {
        return sex;
    }

    public void setSex(Float sex) {
        this.sex = sex;
    }

    public Boolean getAa() {
        return aa;
    }

    public void setAa(Boolean aa) {
        this.aa = aa;
    }

    @Override
    public String toString() {
        return "UserImport{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", aa=" + aa +
                ", monery=" + monery +
                '}';
    }
}
