package com.zongze.rocketmq.entity;

/**
 * Create By xzz on 2020/7/13
 */
public class Order {

    private int id;
    private String orderName;

    private String orderId;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order(int id, String orderName, String orderId) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
