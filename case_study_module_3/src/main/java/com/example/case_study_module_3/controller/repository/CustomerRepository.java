package com.example.case_study_module_3.controller.repository;

import com.example.case_study_module_3.controller.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM customers"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customers.add(mapResultSetToCustomer(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all customers", e);
        }
        return customers;
    }

    public void save(Customer customer) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "INSERT INTO customers (customer_name, customer_email, customer_password, customer_phone, customer_address) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );
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
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "DELETE FROM customers WHERE customer_id = ?"
            );
            statement.setInt(1, customerId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting customer by ID", e);
        }
    }

    public boolean update(Customer customer) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "UPDATE customers SET customer_name = ?, customer_email = ?, customer_password = ?, customer_phone = ?, customer_address = ? " +
                            "WHERE customer_id = ?"
            );
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getCustomerEmail());
            statement.setString(3, customer.getCustomerPassword());
            statement.setString(4, customer.getCustomerPhone());
            statement.setString(5, customer.getCustomerAddress());
            statement.setInt(6, customer.getCustomerId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    public boolean updateIsAdmin(int customerId, boolean newRole) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "UPDATE customers SET role = ? WHERE customer_id = ?"
            );
            statement.setBoolean(1, newRole);
            statement.setInt(2, customerId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer role", e);
        }
    }

    public Customer findById(int customerId) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM customers WHERE customer_id = ?"
            );
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCustomer(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding customer by ID", e);
        }
        return null;
    }

    public boolean isEmailExists(String email) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM customers WHERE customer_email = ?"
            );
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence", e);
        }
    }

    public boolean register(Customer customer) {
        if (isEmailExists(customer.getCustomerEmail())) {
            return false;
        }
        customer.setIsAdmin(false);
        save(customer);
        return true;
    }

    public Customer login(String email, String password) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM customers WHERE customer_email = ? AND customer_password = ?"
            );
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCustomer(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error logging in", e);
        }
        return null;
    }

    public Customer findByEmail(String email) {
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(
                    "SELECT * FROM customers WHERE customer_email = ?"
            );
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCustomer(resultSet);
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
