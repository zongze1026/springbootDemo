package com.zongze.netty.http;

import com.zongze.util.HttpUtil;

/**
 * Create By xzz on 2020/4/21
 */
public class Order {

    private Integer id;

    private String orderName;

    private Double price;

    public Order(Integer id, String orderName, Double price) {
        this.id = id;
        this.orderName = orderName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



}
