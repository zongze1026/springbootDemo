package com.zongze.service;

/**
 * @Date 2020/7/22 21:40
 * @Created by xzz
 */
public class Student {


    private String name;

    public Student(String name) {
        this.name = name;
    }

    {
        System.out.println("构造代码块被调用");
    }


    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            new Student(""+i);
        }
    }


}
