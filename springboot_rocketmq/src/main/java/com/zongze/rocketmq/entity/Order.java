package com.zongze.rocketmq.entity;

/**
 * Create By xzz on 2020/7/13
 */
public class Order {

    private String orderName;

    private String orderId;

    public Order() {
    }

    public Order(String orderName, String orderId) {
        this.orderName = orderName;
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
