package com.example.case_study_module_3.service.impl;

import com.example.case_study_module_3.model.CartItem;
import com.example.case_study_module_3.service.ICartService;

import java.util.List;
public class CartServiceImpl implements ICartService {
    @Override
    public double calculateTotal(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

}
