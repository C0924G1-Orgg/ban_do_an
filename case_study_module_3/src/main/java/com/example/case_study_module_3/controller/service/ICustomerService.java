package com.example.case_study_module_3.controller.service;

import com.example.case_study_module_3.controller.model.Customer;

public interface ICustomerService extends IService<Customer> {
    Customer login(String email, String password); // Xác thực người dùng
    boolean register(Customer customer);          // Đăng ký người dùng mới
    boolean isEmailExists(String email);          // Kiểm tra email đã tồn tại
    Customer findById(int id);

    Customer findByEmail(String email);
}
