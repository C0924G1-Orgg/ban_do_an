package com.example.entity;

import java.util.Objects;

public class Food {
    private int foodId;
    private int restaurantId;
    private String foodName;
    private String foodDescription;
    private double foodPrice;

    public Food() {
    }

    public Food(int foodId, int restaurantId, String foodName, String foodDescription, double foodPrice) {
        this.foodId = foodId;
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
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
