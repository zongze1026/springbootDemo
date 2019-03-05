package com.zongze.entity;

import com.alibaba.fastjson.JSON;

/**
 * Create By xzz on 2019/2/25
 */
public enum TestEnum {
    Test("ldsa",2),
    Test_02("23541",4),
    Test_01("dsfas",5);

    private String name;

    private int age;

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

    TestEnum(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        TestEnum test = TestEnum.valueOf("Test");
        System.out.println(JSON.toJSONString(test));
        System.out.println(test.equals(TestEnum.Test));
    }

}
