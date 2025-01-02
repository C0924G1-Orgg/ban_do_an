package com.example.case_study_module_3.controller.service;



import com.example.case_study_module_3.controller.dto.FoodDTO;
import com.example.case_study_module_3.controller.model.Food;

import java.util.List;

public interface IFoodService extends IService<Food> {
    List<FoodDTO> getAllDTO();
    List<Food> searchByName(String name);
    Food findByID(int food_id);

}
