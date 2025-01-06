package com.example.case_study_module_3.service;


import com.example.case_study_module_3.model.CartItem;

import java.util.List;

public interface ICartService {
    double calculateTotal(List<CartItem> cartItems) ;
}
