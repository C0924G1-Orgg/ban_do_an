package com.example.case_study_module_3.controller.model;

import java.util.List;

public class Order {
    private int orderId;
    private List<CartItem> items;
    private double totalAmount;

    // Constructors, getters, and setters
    public Order(int orderId, List<CartItem> items, double totalAmount) {
        this.orderId = orderId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
