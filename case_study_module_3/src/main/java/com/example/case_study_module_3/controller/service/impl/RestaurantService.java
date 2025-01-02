package com.example.case_study_module_3.controller.service.impl;

import com.example.case_study_module_3.controller.model.Restaurant;
import com.example.case_study_module_3.controller.repository.RestaurantRepository;
import com.example.case_study_module_3.controller.service.IRestaurantService;

import java.util.List;

public class RestaurantService implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService() {
        this.restaurantRepository = new RestaurantRepository();
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @Override
    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public boolean deleteById(int id) {
        return restaurantRepository.deleteById(id);
    }

    @Override
    public boolean update(Restaurant restaurant) {
        return restaurantRepository.update(restaurant);
    }

    @Override
    public List<Restaurant> searchByName(String name) {
        return restaurantRepository.searchByName(name);
    }
}