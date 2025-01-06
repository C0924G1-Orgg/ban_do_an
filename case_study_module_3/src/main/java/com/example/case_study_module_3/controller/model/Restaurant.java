package com.example.case_study_module_3.controller.model;

import java.util.Objects;

public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantEmail;

    public Restaurant() {
    }

    public Restaurant(int restaurantId, String restaurantName, String restaurantAddress, String restaurantPhone, String restaurantEmail) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhone = restaurantPhone;
        this.restaurantEmail = restaurantEmail;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return restaurantId == that.restaurantId && Objects.equals(restaurantEmail, that.restaurantEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, restaurantEmail);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurant_id=" + restaurantId +
                ", restaurant_name='" + restaurantName + '\'' +
                ", restaurant_address='" + restaurantAddress + '\'' +
                ", restaurant_phone='" + restaurantPhone + '\'' +
                ", restaurant_email='" + restaurantEmail + '\'' +
                '}';
    }
}
