package com.example.case_study_module_3.service;

import com.example.case_study_module_3.model.Customer;

public interface ICustomerService extends IService<Customer> {
    Customer login(String email, String password);
    boolean register(Customer customer);
    boolean isEmailExists(String email);
    Customer findById(int id);
    Customer findByEmail(String email);
    boolean updateIsAdmin(int id, boolean isAdmin);
}
