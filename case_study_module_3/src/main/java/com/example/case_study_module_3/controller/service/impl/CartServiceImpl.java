package com.example.case_study_module_3.controller.service.impl;


import com.example.case_study_module_3.controller.model.CartItem;
import com.example.case_study_module_3.controller.service.ICartService;

import java.util.ArrayList;
import java.util.List;
public class CartServiceImpl implements ICartService {
    private List<CartItem> cartItems = new ArrayList<>();

    @Override
    public void addToCart(CartItem cartItem) {
        boolean exists = false;

        for (CartItem item : cartItems) {
            if (item.getId() == cartItem.getId()) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                exists = true;
                break;
            }
        }

        if (!exists) {
            cartItems.add(cartItem);
        }
    }

    @Override
    public void updateQuantity(int id, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getId() == id) {
                item.setQuantity(quantity);
                break;
            }
        }
    }

    @Override
    public void removeFromCart(int id) {
        cartItems.removeIf(item -> item.getId() == id);
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
