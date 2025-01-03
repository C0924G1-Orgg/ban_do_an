package com.example.case_study_module_3.controller.model;

import java.util.Objects;

public class Food {
    private int foodId;
    private int restaurantId;
    private String foodName;
    private String foodDescription;
    private double foodPrice;
    private String foodImage;
    private String restaurantName; // Tên nhà hàng


    public Food(int foodId, int restaurantId, String foodName, String foodDescription, double foodPrice, String foodImage) {
        this.foodId = foodId;
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.foodImage = foodImage;
    }

    public Food() {
    }
    public Food(int foodId, int restaurantId, String foodName, String foodDescription, double foodPrice) {
        this.foodId = foodId;
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
    }

    public Food(int foodId, int restaurantId, String foodName, String foodDescription, double foodPrice, String foodImage, String restaurantName) {
        this.foodId = foodId;
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.foodImage = foodImage;
        this.restaurantName = restaurantName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getImage() {
        return foodImage;
    }

    public void setImage(String image) {
        this.foodImage = image;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food menu = (Food) o;
        return foodId == menu.foodId && Objects.equals(foodName, menu.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodId, foodName);
    }

    @Override
    public String toString() {
        return "menu{" +
                "food_id" + foodId +
                "restaurant_id" + restaurantId +
                "food_name" + foodName +
                "food_description" + foodDescription +
                "food_price" + foodPrice + "}";
    }
}
