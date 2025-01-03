package com.example.case_study_module_3.controller.service;


import com.example.case_study_module_3.controller.model.CartItem;

import java.util.List;

public interface ICartService {
    double calculateTotal(List<CartItem> cartItems) ;
}
