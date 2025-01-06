package com.example.case_study_module_3.controller.repository;


import com.example.case_study_module_3.controller.model.Restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    public List<Restaurant> getAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM restaurants"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int restaurant_id = resultSet.getInt("restaurant_id");
                String restaurant_name = resultSet.getString("restaurant_name");
                String restaurant_address = resultSet.getString("restaurant_address");
                String restaurant_phone = resultSet.getString("restaurant_phone");
                String restaurant_email = resultSet.getString("restaurant_email");
                restaurants.add(new Restaurant(restaurant_id, restaurant_name, restaurant_address, restaurant_phone, restaurant_email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return restaurants;
    }

    public void save(Restaurant restaurant) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "INSERT INTO restaurants (restaurant_name, restaurant_address, restaurant_phone, restaurant_email) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, restaurant.getRestaurantName());
            statement.setString(2, restaurant.getRestaurantAddress());
            statement.setString(3, restaurant.getRestaurantPhone());
            statement.setString(4, restaurant.getRestaurantEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(int restaurant_id) {
        try {
            PreparedStatement checkStatement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM restaurants WHERE restaurant_id = ?"
            );
            checkStatement.setInt(1, restaurant_id);
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }

            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "DELETE FROM restaurants WHERE restaurant_id = ?"
            );
            statement.setInt(1, restaurant_id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Restaurant restaurant) {
        try {
            PreparedStatement checkStatement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM restaurants WHERE restaurant_id = ?"
            );
            checkStatement.setInt(1, restaurant.getRestaurantId());
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }

            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "UPDATE restaurants SET restaurant_name = ?, restaurant_address = ?, restaurant_phone = ?, restaurant_email = ? WHERE restaurant_id = ?"
            );
            statement.setString(1, restaurant.getRestaurantName());
            statement.setString(2, restaurant.getRestaurantAddress());
            statement.setString(3, restaurant.getRestaurantPhone());
            statement.setString(4, restaurant.getRestaurantEmail());
            statement.setInt(5, restaurant.getRestaurantId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Restaurant> searchByName(String name) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM restaurants WHERE restaurant_name LIKE ?"
            );
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int restaurant_id = resultSet.getInt("restaurant_id");
                String restaurant_name = resultSet.getString("restaurant_name");
                String restaurant_address = resultSet.getString("restaurant_address");
                String restaurant_phone = resultSet.getString("restaurant_phone");
                String restaurant_email = resultSet.getString("restaurant_email");
                restaurants.add(new Restaurant(restaurant_id, restaurant_name, restaurant_address, restaurant_phone, restaurant_email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return restaurants;
    }
}
