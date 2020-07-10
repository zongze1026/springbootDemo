package com.zongze.weakreference;

/**
 * Create By xzz on 2020/7/10
 */
public class Student {
    private String name;

    public String sayName() {
        return "我的名字叫：" + name;
    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(name+"被gc回收了");
    }

}
