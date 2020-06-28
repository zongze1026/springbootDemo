package com.zongze.service;

import org.openjdk.jol.info.ClassLayout;

/**
 * Create By xzz on 2020/6/28
 */
public class ClassLayOutTest {


    public static void main(String[] args) {

        ClassLayout classlayout = ClassLayout.parseInstance(new Object());
        System.out.println(classlayout.toPrintable());
    }


}
