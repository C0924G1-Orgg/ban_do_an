package com.example.service;

import com.example.entity.Restaurant;

import java.util.List;

public interface IRestaurantService extends IService<Restaurant> {
    List<Restaurant> searchByName(String name);
}
