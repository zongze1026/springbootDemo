package com.zongze.ref;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by xieZZ on 2019/2/24
 */
@Setter
@Getter
public class User extends Parent {

    private String userName;

    private Integer age;

    private Date birthday;

    private static long id = 100;




    public static void main(String[] args) {
        Class<? extends User> aClass = new User().getClass();
        Field[] fields = aClass.getDeclaredFields();
        System.out.println(JSON.toJSONString(fields));
        for (Field field:fields){
            System.out.println(field.getName());
        }
        echo();
        Field[] pf = aClass.getSuperclass().getDeclaredFields();
        for (Field field:pf){
            System.out.println(field.getName());
        }

        echo();

    }

    private static void echo() {
        System.out.println(User.class.isEnum());
        System.out.println(EM.class.isEnum());
    }


}
