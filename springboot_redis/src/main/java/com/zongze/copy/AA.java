package com.zongze.copy;
import java.io.*;

/**
 * @Date 2021/5/21 15:03
 * @Created by xiezz
 *
 * 测试对象深浅拷贝
 *
 */
public class AA {


    public static void main(String[] args) throws CloneNotSupportedException {
//        deepCopyOne();


        deepCopyTwo();


    }


    /**
     * 方式二：采用序列化的方式进行对象深拷贝
     * 注意：被拷贝的类需要实现序列化接口（Serializable）
     */
    private static void deepCopyTwo() {
        Teacher teacher = new Teacher("语文课-李老师");
        Student student = new Student("小张");
        School school = new School(teacher,student);

        School clone = (School) doCopyObject(school);
        System.out.println(school == clone);
        System.out.println(school.getTeacher() == clone.getTeacher());
        System.out.println(school.getStudent() == clone.getStudent());
    }

    /**
     * 方式一：采用重写object类中clone方法实现
     * 注意：前提需要实现克隆接口（Cloneable）
     */
    private static void deepCopyOne() throws CloneNotSupportedException {
        Teacher teacher = new Teacher("语文课-张老师");
        Student student = new Student("小明");
        School school = new School(teacher,student);

        School clone = school.clone();
        System.out.println(school == clone);
        System.out.println(school.getTeacher() == clone.getTeacher());
        System.out.println(school.getStudent() == clone.getStudent());
    }


    /**
     * 序列化拷贝工具
     */
    public static Object doCopyObject(Object source){
        Object target = null;
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos = null;
        ObjectInputStream ois = null;
        ByteArrayInputStream bis = null;

        try {
            oos = new ObjectOutputStream(bos = new ByteArrayOutputStream());
            oos.writeObject(source);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            target = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
                oos.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return target;
    }



}
