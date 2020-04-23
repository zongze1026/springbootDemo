package com.zongze.netty.protobuf;

import java.io.Serializable;

/**
 * Create By xzz on 2020/4/23
 */
public class Book implements Serializable {
    private String bookName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }
}
