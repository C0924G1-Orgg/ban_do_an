package com.example.service;

import com.example.dto.FoodDTO;
import com.example.entity.Food;

import java.util.List;

public interface IFoodService extends IService<Food> {
    List<FoodDTO> getAllDTO();
    List<Food> searchByName(String name);
    Food findByID(int food_id);

}
