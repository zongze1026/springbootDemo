package com.zongze.entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Create By xzz on 2019/2/21
 */
@Setter
@Getter
public class SetterAndGetter {

    private String name;

    private Integer age;


    public static void main(String[] args) {
        SetterAndGetter instance = new SetterAndGetter();
        instance.setAge(10);
        instance.setName("test");
        System.out.println(instance.getName());
        System.out.println(instance.getAge());

    }



}
