package com.example.service.impl;

import com.example.dto.FoodDTO;
import com.example.entity.Food;
import com.example.repository.FoodRepository;
import com.example.service.IFoodService;

import java.util.List;

public class FoodService implements IFoodService {

    private final FoodRepository foodRepository = new FoodRepository();

    @Override
    public List<Food> getAll() {
        return foodRepository.getAll();
    }

    @Override
    public void save(Food food) {
        foodRepository.save(food);
    }

    @Override
    public boolean deleteById(int id) {
        return foodRepository.deleteById(id);
    }

    @Override
    public boolean update(Food food) {
        return foodRepository.update(food);
    }

    @Override
    public List<FoodDTO> getAllDTO() {
        return foodRepository.getAllDTO();
    }

    @Override
    public List<Food> searchByName(String name) {
        return foodRepository.searchByName(name);
    }

    @Override
    public Food findByID(int food_id) {
        return foodRepository.findById(food_id);
    }
}
