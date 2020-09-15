package com.zongze.service;

import java.util.ArrayList;
import java.util.List;

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
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(8);

        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(3);
        list2.add(4);
        list2.add(1);
        list2.add(2);
        list2.add(29);


        boolean b = list2.retainAll(list1);
        System.out.println(b);
    }


}
