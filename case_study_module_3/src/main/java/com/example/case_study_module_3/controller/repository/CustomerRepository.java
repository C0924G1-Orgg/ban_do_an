package com.example.case_study_module_3.controller.repository;

import com.example.case_study_module_3.controller.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private Connection getConnection() throws SQLException {
        return BaseRepository.getConnection();
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                customers.add(mapResultSetToCustomer(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all customers", e);
        }
        return customers;
    }

    public void save(Customer customer) {
        String query = "INSERT INTO customers (customer_name, customer_email, customer_password, customer_phone, customer_address, customer_is_admin) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getCustomerEmail());
            statement.setString(3, customer.getCustomerPassword());
            statement.setString(4, customer.getCustomerPhone());
            statement.setString(5, customer.getCustomerAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving customer", e);
        }
    }

    public boolean deleteById(int customerId) {
        String query = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting customer by ID", e);
        }
    }

    public boolean update(Customer customer) {
        String query = "UPDATE customers SET customer_name = ?, customer_email = ?, customer_password = ?, customer_phone = ?, customer_address = ? WHERE customer_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getCustomerEmail());
            statement.setString(3, customer.getCustomerPassword());
            statement.setString(4, customer.getCustomerPhone());
            statement.setString(5, customer.getCustomerAddress());
            statement.setInt(6, customer.getCustomerId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    public boolean updateIsAdmin(int customerId, boolean newRole) {
        String query = "UPDATE customers SET customer_is_admin = ? WHERE customer_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, newRole);
            statement.setInt(2, customerId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer role", e);
        }
    }

    public Customer findById(int customerId) {
        String query = "SELECT * FROM customers WHERE customer_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding customer by ID", e);
        }
        return null;
    }

    public boolean isEmailExists(String email) {
        String query = "SELECT * FROM customers WHERE customer_email = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence", e);
        }
    }

    public boolean register(Customer customer) {
        if (isEmailExists(customer.getCustomerEmail())) {
            return false; // Email đã tồn tại
        }
        String query = "INSERT INTO customers (customer_name, customer_email, customer_password, customer_phone, customer_address, customer_is_admin) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getCustomerEmail());
            statement.setString(3, customer.getCustomerPassword());
            statement.setString(4, customer.getCustomerPhone());
            statement.setString(5, customer.getCustomerAddress());
            statement.setBoolean(6, customer.isAdmin());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error registering customer", e);
        }
    }

    public Customer login(String email, String password) {
        String query = "SELECT * FROM customers WHERE customer_email = ? AND customer_password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error logging in", e);
        }
        return null;
    }

    public Customer findByEmail(String email) {
        String query = "SELECT * FROM customers WHERE customer_email = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding customer by email", e);
        }
        return null;
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("customer_id");
        String name = resultSet.getString("customer_name");
        String email = resultSet.getString("customer_email");
        String password = resultSet.getString("customer_password");
        String phone = resultSet.getString("customer_phone");
        String address = resultSet.getString("customer_address");
        boolean isAdmin = resultSet.getBoolean("customer_is_admin");
        return new Customer(id, name, email, password, phone, address, isAdmin);
    }
}
