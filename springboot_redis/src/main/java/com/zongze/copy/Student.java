package com.zongze.copy;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date 2021/5/21 15:04
 * @Created by xiezz
 */
@Data
public class Student implements Cloneable,Serializable {

    private String name;

    public Student(String name) {
        this.name = name;
    }
}
