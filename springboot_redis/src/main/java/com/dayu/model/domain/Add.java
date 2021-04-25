package com.dayu.model.domain;

import lombok.Data;

/**
 * @Date 2021/4/9 11:44
 * @Created by xiezz
 */
@Data
public class Add {

    private Integer sort;
    private Integer age;
    private String name;

    public Add(Integer sort, Integer age) {
        this.sort = sort;
        this.age = age;
    }

    public static Integer sum(Integer x, Integer y) {
        return x + y;
    }


    public static void comsumer(String s){
        System.out.println(s);
    }

    public void comsumer1(){
        System.out.println("comsumer1");
    }


    public void comsumer2(String abcd){
        System.out.println("comsumer2");
    }


    public boolean test(){
        return true;
    }

    public int add(Add add){
        return add.getAge()+this.getAge();
    }

}
