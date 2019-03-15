package com.zongze.test;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.messaging.saaj.util.SAAJUtil;
import com.zongze.util.DateUtil;

import javax.sound.midi.Soundbank;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Create By xzz on 2019/3/5
 */
public abstract class Abstranct<T> implements Base {

    private T t;

    protected Abstranct(T t) {
        this.t = t;
    }

    @Override
    public T getObject() {
        return t;
    }


    public static List<?> getList(List<?> list) {
        return list;
    }

    public <T extends Object> T get(List<T> list){
        return null;
    }


    public static void main(String[] args) {
//        ParameterizedType type = (ParameterizedType)Abstranct.class.getAnnotatedSuperclass();
//        System.out.println(JSON.toJSONString(type));
//
//        Calendar ca = Calendar.getInstance();
//        ca.set(Calendar.YEAR,ca.get(Calendar.YEAR));
//        ca.set(Calendar.WEEK_OF_YEAR,ca.get(Calendar.WEEK_OF_YEAR)-1);
//        ca.set(Calendar.DAY_OF_WEEK,1);
//        System.out.println(DateUtil.format(ca.getTime(),DateUtil.DATE_TIME));


        List<String> list = Arrays.asList("abc", "abcd");
        List<Integer> list1 = Arrays.asList(1, 2);
        System.out.println(JSON.toJSONString(Abstranct.getList(list1)));
        System.out.println(JSON.toJSONString(Abstranct.getList(list)));
    }

}
