package com.zongze.test;

import com.alibaba.fastjson.JSON;
import com.zongze.entity.User;


/**
 * Create By xzz on 2019/1/14
 */
public class FieldTest {


    public static void main(String[] args) throws Exception {
        User user = new User("zhangsan");
        user.setUserName("hell");
        User user1 = user;
        user1.setAge(30);
        System.out.println(JSON.toJSONString(user));


    }


    public static String res(String msg){
        return res2(msg);
    }

    private static String res2(String msg) {
        msg="res2 return msg";
        return msg;
    }


}
