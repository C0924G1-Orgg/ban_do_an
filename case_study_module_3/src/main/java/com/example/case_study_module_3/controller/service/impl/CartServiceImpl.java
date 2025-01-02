package com.example.case_study_module_3.controller.service.impl;


import com.example.case_study_module_3.controller.model.CartItem;
import com.example.case_study_module_3.controller.service.ICartService;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements ICartService {
    private final List<CartItem> cartItems = new ArrayList<>();

    @Override
    public void addToCart(CartItem item) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId() == item.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        cartItems.add(item);
    }

    @Override
    public void updateQuantity(int itemId, int quantity) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId() == itemId) {
                cartItem.setQuantity(quantity);
                return;
            }
        }
    }

    @Override
    public void removeFromCart(int itemId) {
        cartItems.removeIf(cartItem -> cartItem.getId() == itemId);
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public double calculateTotal() {
        return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
