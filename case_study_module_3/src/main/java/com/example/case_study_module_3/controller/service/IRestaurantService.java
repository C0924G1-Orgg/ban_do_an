package com.example.case_study_module_3.controller.service;



import com.example.case_study_module_3.controller.model.Restaurant;

import java.util.List;

public interface IRestaurantService extends IService<Restaurant> {
    List<Restaurant> searchByName(String name);
}
