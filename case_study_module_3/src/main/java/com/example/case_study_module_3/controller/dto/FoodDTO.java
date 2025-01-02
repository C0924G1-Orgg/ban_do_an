package com.example.case_study_module_3.controller.dto;

public class FoodDTO {
    private int food_id;
    private int restaurant_id;
    private String food_name;
    private double food_price;
    private String food_description;
    private String restaurant_name;

    public FoodDTO() {
    }

    public FoodDTO(int food_id, int restaurant_id, String food_name, double food_price, String food_description, String restaurant_name) {
        this.food_id = food_id;
        this.restaurant_id = restaurant_id;
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_description = food_description;
        this.restaurant_name = restaurant_name;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public double getFood_price() {
        return food_price;
    }

    public void setFood_price(double food_price) {
        this.food_price = food_price;
    }

    public String getFood_description() {
        return food_description;
    }

    public void setFood_description(String food_description) {
        this.food_description = food_description;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }
}
