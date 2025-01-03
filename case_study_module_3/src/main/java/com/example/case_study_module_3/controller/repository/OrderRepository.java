package com.example.case_study_module_3.controller.repository;

import com.example.case_study_module_3.controller.model.CartItem;
import com.example.case_study_module_3.controller.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderRepository {
    private Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean saveOrder(Order order) {
        String query = "INSERT INTO orders (order_id, total_amount) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getOrderId());
            stmt.setDouble(2, order.getTotalAmount());
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                for (CartItem item : order.getItems()) {
                    saveCartItem(order.getOrderId(), item);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveCartItem(int orderId, CartItem item) throws SQLException {
        String query = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, item.getId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            stmt.executeUpdate();
        }
    }

    public Order findOrderById(int orderId) {
        // Có thể thêm logic tìm đơn hàng theo orderId ở đây.
        // Ví dụ: Truy vấn từ bảng `orders` và `order_items`.
        return null; // Placeholder cho chức năng tìm kiếm.
    }
}
