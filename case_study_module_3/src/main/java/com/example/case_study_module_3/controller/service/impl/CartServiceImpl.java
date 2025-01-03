package com.example.case_study_module_3.controller.service.impl;

import com.example.case_study_module_3.controller.model.CartItem;
import com.example.case_study_module_3.controller.service.ICartService;

import java.util.ArrayList;
import java.util.List;
public class CartServiceImpl implements ICartService {
    @Override
    public double calculateTotal(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

}
