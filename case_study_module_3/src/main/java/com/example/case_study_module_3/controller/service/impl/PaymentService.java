package com.example.case_study_module_3.controller.service.impl;

import com.example.case_study_module_3.controller.model.CartItem;
import com.example.case_study_module_3.controller.model.Order;
import com.example.case_study_module_3.controller.repository.OrderRepository;

import java.util.List;

public class PaymentService {
    private OrderRepository orderRepository;

    public PaymentService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean processPayment(List<CartItem> cartItems, double totalAmount) {
        Order order = new Order(generateOrderId(), cartItems, totalAmount);
        return orderRepository.saveOrder(order);
    }

    private int generateOrderId() {
        return (int) (Math.random() * 10000);
    }
}
