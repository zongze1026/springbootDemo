package com.zongze.netty.protobuf;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By xzz on 2020/4/23
 */
public class Student implements Serializable {

    private Integer age;

    private String userName;

    private List<String> firdents =  new ArrayList<>();

    private List<Book> books = new ArrayList<>();

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getFirdents() {
        return firdents;
    }

    public void setFirdents(List<String> firdents) {
        this.firdents = firdents;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
