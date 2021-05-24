package com.zongze.copy;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date 2021/5/21 15:04
 * @Created by xiezz
 */
@Data
public class School implements Cloneable,Serializable {

    private Teacher teacher;

    private Student student;

    public School(Teacher teacher, Student student) {
        this.teacher = teacher;
        this.student = student;
    }


    @Override
    public School clone() throws CloneNotSupportedException {
        School clone = (School) super.clone();
        clone.teacher = this.teacher.clone();
        return clone;
    }
}
