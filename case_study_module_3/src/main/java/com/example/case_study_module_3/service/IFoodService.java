package com.example.case_study_module_3.service;

import com.example.case_study_module_3.dto.FoodDTO;
import com.example.case_study_module_3.model.Food;

import java.util.List;

public interface IFoodService  extends IService<Food>{
    List<Food> getAll();
    void save(Food food);
    boolean deleteById(int id);
    boolean update(Food food);
    List<FoodDTO> getAllDTO();
    List<Food> searchByName(String name);
    Food findByID(int food_id);

}
