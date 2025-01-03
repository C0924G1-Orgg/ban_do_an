package com.example.case_study_module_3.controller.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private BaseRepository() {
    }

    private static String jdbcURL = "jdbc:mysql://localhost:3306/order_food?useSSL=false";
    private static String jdbcuser = "root";
    private static String jdbcpassword = "Kietanh2301@";
    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcuser, jdbcpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() { return connection; }
}