package com.zongze.test;

import com.zongze.entity.User;


/**
 * Create By xzz on 2019/3/15
 */
public class statictest {


    static{
        System.out.println("静态代码快实例化");
    }
    private static final User user = new User();

    public static void test(){
        System.out.println("============");
    }


}
