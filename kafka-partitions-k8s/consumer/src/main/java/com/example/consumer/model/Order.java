package com.example.consumer.model;

import java.util.Date;

public class Order {

    private int orderId;
    private Date date;

    public Order(int orderId, Date date) {
        this.orderId = orderId;
        this.date = date;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
