package com.zongze.test;

import com.zongze.entity.User;


/**
 * Create By xzz on 2019/3/15
 */
public class statictest extends Super {

    private static final User user = new User("子类");

    private final User user1 = new User("子类,非静态");

    static{
        System.out.println("子类静态代码快实例化");
    }

    public statictest() {
        System.out.println("子类构造执行");
    }

    public static void test(){
        System.out.println("============");
    }


}
