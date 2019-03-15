package com.zongze.entity;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By xzz on 2019/2/25
 */
public enum TestEnum {
    Test("ldsa", 2),
    Test_01("dsfas", 5);

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
        Map<String,Integer>data = new HashMap<>();
        for (TestEnum testEnum : TestEnum.values()) {
            data.put(testEnum.getName(),testEnum.getAge());
        }
        System.out.println(JSON.toJSONString(data));
    }

}
