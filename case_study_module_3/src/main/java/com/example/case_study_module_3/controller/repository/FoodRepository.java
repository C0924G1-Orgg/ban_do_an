package com.example.case_study_module_3.controller.repository;

import com.example.case_study_module_3.controller.dto.FoodDTO;
import com.example.case_study_module_3.controller.model.Food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository {

    public List<Food> getAll() {
        List<Food> foods = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM foods"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int food_id = resultSet.getInt("food_id");
                int restaurant_id = resultSet.getInt("restaurant_id");
                String food_name = resultSet.getString("food_name");
                String food_description = resultSet.getString("food_description");
                double food_price = resultSet.getDouble("food_price");
                foods.add(new Food(food_id, restaurant_id, food_name, food_description, food_price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foods;
    }

    public List<FoodDTO> getAllDTO() {
        List<FoodDTO> foodDTOs = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT foods.food_id, foods.restaurant_id, foods.food_name, foods.food_price, " +
                            "foods.food_description, restaurants.restaurant_name " +
                            "FROM foods " +
                            "JOIN restaurants ON foods.restaurant_id = restaurants.restaurant_id"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int food_id = resultSet.getInt("food_id");
                int restaurant_id = resultSet.getInt("restaurant_id");
                String food_name = resultSet.getString("food_name");
                double food_price = resultSet.getDouble("food_price");
                String food_description = resultSet.getString("food_description");
                String restaurant_name = resultSet.getString("restaurant_name");
                foodDTOs.add(new FoodDTO(food_id, restaurant_id, food_name, food_price, food_description, restaurant_name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foodDTOs;
    }

    public void save(Food food) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "INSERT INTO foods (restaurant_id, food_name, food_description, food_price) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, food.getRestaurant_id());
            statement.setString(2, food.getFoodName());
            statement.setString(3, food.getFoodDescription());
            statement.setDouble(4, food.getFoodPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(int food_id) {
        try {
            PreparedStatement checkStatement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM foods WHERE food_id = ?"
            );
            checkStatement.setInt(1, food_id);
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }

            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "DELETE FROM foods WHERE food_id = ?"
            );
            statement.setInt(1, food_id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Food food) {
        try {
            PreparedStatement checkStatement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM foods WHERE food_id = ?"
            );
            checkStatement.setInt(1, food.getFood_id());
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }

            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "UPDATE foods SET restaurant_id = ?, food_name = ?, food_description = ?, food_price = ? WHERE food_id = ?"
            );
            statement.setInt(1, food.getRestaurant_id());
            statement.setString(2, food.getFoodName());
            statement.setString(3, food.getFoodDescription());
            statement.setDouble(4, food.getFoodPrice());
            statement.setInt(5, food.getFood_id());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Food> searchByName(String name) {
        List<Food> foods = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM foods WHERE food_name LIKE ?"
            );
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int food_id = resultSet.getInt("food_id");
                int restaurant_id = resultSet.getInt("restaurant_id");
                String food_name = resultSet.getString("food_name");
                String food_description = resultSet.getString("food_description");
                double food_price = resultSet.getDouble("food_price");
                foods.add(new Food(food_id, restaurant_id, food_name, food_description, food_price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foods;
    }

    public Food findById(int food_id) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM foods WHERE food_id = ?"
            );
            statement.setInt(1, food_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int restaurant_id = resultSet.getInt("restaurant_id");
                String food_name = resultSet.getString("food_name");
                String food_description = resultSet.getString("food_description");
                double food_price = resultSet.getDouble("food_price");
                return new Food(food_id, restaurant_id, food_name, food_description, food_price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
