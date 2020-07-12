package com.zongze.weakreference;

import java.lang.ref.WeakReference;

/**
 * Create By xzz on 2020/7/10
 */
public class Classess extends WeakReference<Student> {

    public Classess(Student referent) {
        super(referent);
    }

    public void sayName() {
        Student student = get();
        if (null == student) {
            System.out.println("班级中没有学生啦");
        } else {
            System.out.println(student.sayName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Student student = new Student("toms");
        Classess classess = new Classess(student);
        classess.sayName();
        //调用系统gc
        student = null;
        System.gc();
        Thread.sleep(2000);
        classess.sayName();
    }
}
