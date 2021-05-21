package com.zongze.copy;

import lombok.Data;

/**
 * @Date 2021/5/21 15:04
 * @Created by xiezz
 */
@Data
public class Student implements Cloneable{

    private String name;

    public Student(String name) {
        this.name = name;
    }
}
