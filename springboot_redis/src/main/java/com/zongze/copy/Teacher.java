package com.zongze.copy;

import lombok.Data;

/**
 * @Date 2021/5/21 15:04
 * @Created by xiezz
 */
@Data
public class Teacher implements Cloneable{

    private String name;

    public Teacher(String name) {
        this.name = name;
    }


    @Override
    public Teacher clone() throws CloneNotSupportedException {
        return (Teacher) super.clone();
    }
}
