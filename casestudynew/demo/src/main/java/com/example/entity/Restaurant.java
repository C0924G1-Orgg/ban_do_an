package com.example.entity;

import java.util.Objects;

public class Restaurant {
    private int restaurant_id;
    private String restaurant_name;
    private String restaurant_address;
    private String restaurant_phone;
    private String restaurant_email;

    public Restaurant() {
    }

    public Restaurant(int restaurant_id, String restaurant_name, String restaurant_address, String restaurant_phone, String restaurant_email) {
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.restaurant_phone = restaurant_phone;
        this.restaurant_email = restaurant_email;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_address() {
        return restaurant_address;
    }

    public void setRestaurant_address(String restaurant_address) {
        this.restaurant_address = restaurant_address;
    }

    public String getRestaurant_phone() {
        return restaurant_phone;
    }

    public void setRestaurant_phone(String restaurant_phone) {
        this.restaurant_phone = restaurant_phone;
    }

    public String getRestaurant_email() {
        return restaurant_email;
    }

    public void setRestaurant_email(String restaurant_email) {
        this.restaurant_email = restaurant_email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return restaurant_id == that.restaurant_id && Objects.equals(restaurant_email, that.restaurant_email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurant_id, restaurant_email);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurant_id=" + restaurant_id +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", restaurant_address='" + restaurant_address + '\'' +
                ", restaurant_phone='" + restaurant_phone + '\'' +
                ", restaurant_email='" + restaurant_email + '\'' +
                '}';
    }
}
