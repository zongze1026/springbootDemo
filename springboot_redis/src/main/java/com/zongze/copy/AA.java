package com.zongze.copy;

/**
 * @Date 2021/5/21 15:03
 * @Created by xiezz
 *
 * 测试对象深浅拷贝
 *
 */
public class AA {


    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher teacher = new Teacher("语文课-张老师");
        Student student = new Student("小明");
        School school = new School(teacher,student);

        School clone = school.clone();
        System.out.println(school == clone);
        System.out.println(school.getTeacher() == clone.getTeacher());
        System.out.println(school.getStudent() == clone.getStudent());


    }



}
