package com.example.case_study_module_3.controller.dto;

public class OrderDTO {
    private int orderId;
    private int customerId;
    private int foodId;
    private int restaurantId;
    private String orderDate;
    private double totalOrderPrice;
    private String orderStatus;
    private String customerName;
    private String foodName;
    private String restaurantName;

    public OrderDTO(int orderId, int customerId, int foodId, int restaurantId, String orderDate, double totalOrderPrice, String orderStatus) {
    }

    public OrderDTO(int orderId, int customerId, int foodId, int restaurantId, String orderDate, double totalOrderPrice, String orderStatus, String customerName, String foodName, String restaurantName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.foodId = foodId;
        this.restaurantId = restaurantId;
        this.orderDate = orderDate;
        this.totalOrderPrice = totalOrderPrice;
        this.orderStatus = orderStatus;
        this.customerName = customerName;
        this.foodName = foodName;
        this.restaurantName = restaurantName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}