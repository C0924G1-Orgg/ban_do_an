package com.example.case_study_module_3.controller.model;

import java.util.Objects;

public class Food {
    private int food_id;
    private int restaurant_id;
    private String food_name;
    private String food_description;
    private double food_price;
    private String food_image;

    public Food(int food_id, int restaurant_id, String food_name, String food_description, double food_price, String food_image) {
        this.food_id = food_id;
        this.restaurant_id = restaurant_id;
        this.food_name = food_name;
        this.food_description = food_description;
        this.food_price = food_price;
        this.food_image = food_image;
    }

    public Food() {
    }
    public Food(int food_id, int restaurant_id, String food_name, String food_description, double food_price) {
        this.food_id = food_id;
        this.restaurant_id = restaurant_id;
        this.food_name = food_name;
        this.food_description = food_description;
        this.food_price = food_price;
    }

    public String getImage() {
        return food_image;
    }

    public void setImage(String image) {
        this.food_image = image;
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

    public String getFoodName() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFoodDescription() {
        return food_description;
    }

    public void setFood_description(String food_description) {
        this.food_description = food_description;
    }

    public double getFoodPrice() {
        return food_price;
    }

    public void setFood_price(double food_price) {
        this.food_price = food_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food menu = (Food) o;
        return food_id == menu.food_id && Objects.equals(food_name, menu.food_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food_id, food_name);
    }

    @Override
    public String toString() {
        return "menu{" +
                "food_id" + food_id +
                "restaurant_id" + restaurant_id +
                "food_name" + food_name +
                "food_description" + food_description +
                "food_price" + food_price + "}";
    }
}
