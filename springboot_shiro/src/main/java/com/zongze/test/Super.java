package com.zongze.test;

import com.zongze.entity.User;

import javax.security.auth.Subject;

/**
 * Create By xzz on 2019/3/15
 */
public class Super {

    private static final User user = new User("父类");

    private final User user1 = new User("父类,非静态");

    public Super() {
        System.out.println("父类构造被执行");
    }

    static{
        System.out.println("父类静态代码快实例化");
    }


    public static void main(String[] args) {
        Class<? super Super> superclass = Super.class.getSuperclass();
        System.out.println(superclass.getName());
    }



}
