package com.example.case_study_module_3.controller.service;


import com.example.case_study_module_3.controller.model.CartItem;

import java.util.List;

public interface ICartService {
    void addToCart(CartItem item);
    void updateQuantity(int itemId, int quantity);
    void removeFromCart(int itemId);
    List<CartItem> getCartItems();
    double calculateTotal();
}
