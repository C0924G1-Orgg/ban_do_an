package com.example.case_study_module_3.controller.repository;

import com.example.case_study_module_3.controller.dto.OrderDTO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    public List<OrderDTO> getAll() {
        List<OrderDTO> orders = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM orders"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int customerId = resultSet.getInt("customer_id");
                int foodId = resultSet.getInt("food_id");
                int restaurantId = resultSet.getInt("restaurant_id");
                String orderDate = resultSet.getString("order_date");
                double totalOrderPrice = resultSet.getDouble("total_order_price");
                String orderStatus = resultSet.getString("order_status");
                orders.add(new OrderDTO(orderId, customerId, foodId, restaurantId, orderDate, totalOrderPrice, orderStatus));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public List<OrderDTO> getAllDTO() {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT orders.order_id, orders.customer_id, orders.food_id, orders.restaurant_id, orders.order_date, orders.total_order_price, orders.order_status, " +
                            "customers.customer_name, foods.food_name, restaurants.restaurant_name " +
                            "FROM orders " +
                            "JOIN customers ON orders.customer_id = customers.customer_id " +
                            "JOIN foods ON orders.food_id = foods.food_id " +
                            "JOIN restaurants ON orders.restaurant_id = restaurants.restaurant_id"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int customerId = resultSet.getInt("customer_id");
                int foodId = resultSet.getInt("food_id");
                int restaurantId = resultSet.getInt("restaurant_id");
                String orderDate = resultSet.getString("order_date");
                double totalOrderPrice = resultSet.getDouble("total_order_price");
                String orderStatus = resultSet.getString("order_status");
                String customerName = resultSet.getString("customer_name");
                String foodName = resultSet.getString("food_name");
                String restaurantName = resultSet.getString("restaurant_name");
                orderDTOs.add(new OrderDTO(orderId, customerId, foodId, restaurantId, orderDate, totalOrderPrice, orderStatus, customerName, foodName, restaurantName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDTOs;
    }

    public void save(OrderDTO order) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "INSERT INTO orders (customer_id, food_id, restaurant_id, order_date, total_order_price, order_status) VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setInt(1, order.getCustomerId());
            statement.setInt(2, order.getFoodId());
            statement.setInt(3, order.getRestaurantId());
            statement.setString(4, order.getOrderDate());
            statement.setDouble(5, order.getTotalOrderPrice());
            statement.setString(6, order.getOrderStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(int orderId) {
        try {
            PreparedStatement checkStatement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM orders WHERE order_id = ?"
            );
            checkStatement.setInt(1, orderId);
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }

            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "DELETE FROM orders WHERE order_id = ?"
            );
            statement.setInt(1, orderId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(OrderDTO order) {
        try {
            PreparedStatement checkStatement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM orders WHERE order_id = ?"
            );
            checkStatement.setInt(1, order.getOrderId());
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }

            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "UPDATE orders SET customer_id = ?, food_id = ?, restaurant_id = ?, order_date = ?, total_order_price = ?, order_status = ? WHERE order_id = ?"
            );
            statement.setInt(1, order.getCustomerId());
            statement.setInt(2, order.getFoodId());
            statement.setInt(3, order.getRestaurantId());
            statement.setString(4, order.getOrderDate());
            statement.setDouble(5, order.getTotalOrderPrice());
            statement.setString(6, order.getOrderStatus());
            statement.setInt(7, order.getOrderId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderDTO> searchByCustomerName(String customerName) {
        List<OrderDTO> orders = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT orders.* FROM orders " +
                            "JOIN customers ON orders.customer_id = customers.customer_id " +
                            "WHERE customers.customer_name LIKE ?"
            );
            statement.setString(1, "%" + customerName + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int customerId = resultSet.getInt("customer_id");
                int foodId = resultSet.getInt("food_id");
                int restaurantId = resultSet.getInt("restaurant_id");
                String orderDate = resultSet.getString("order_date");
                double totalOrderPrice = resultSet.getDouble("total_order_price");
                String orderStatus = resultSet.getString("order_status");
                orders.add(new OrderDTO(orderId, customerId, foodId, restaurantId, orderDate, totalOrderPrice, orderStatus));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public OrderDTO findById(int orderId) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM orders WHERE order_id = ?"
            );
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                int foodId = resultSet.getInt("food_id");
                int restaurantId = resultSet.getInt("restaurant_id");
                String orderDate = resultSet.getString("order_date");
                double totalOrderPrice = resultSet.getDouble("total_order_price");
                String orderStatus = resultSet.getString("order_status");
                return new OrderDTO(orderId, customerId, foodId, restaurantId, orderDate, totalOrderPrice, orderStatus);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}