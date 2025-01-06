package com.example.case_study_module_3.controller.service.impl;

import com.example.case_study_module_3.controller.model.Customer;
import com.example.case_study_module_3.controller.repository.CustomerRepository;
import com.example.case_study_module_3.controller.service.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean deleteById(int id) {
        return customerRepository.deleteById(id);
    }

    @Override
    public boolean update(Customer customer) {
        return customerRepository.update(customer);
    }

    @Override
    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && customer.getCustomerPassword().equals(password)) {
            return customer; // Xác thực thành công
        }
        return null; // Xác thực thất bại
    }

    @Override
    public boolean register(Customer customer) {
        // Kiểm tra email đã tồn tại
        if (isEmailExists(customer.getCustomerEmail())) {
            return false; // Email đã tồn tại
        }
        // Thêm người dùng mới
        customerRepository.save(customer);
        return true;
    }

    @Override
    public boolean isEmailExists(String email) {
        Customer customer = customerRepository.findByEmail(email);
        return customer != null;
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    @Override
    public boolean updateIsAdmin(int id, boolean isAdmin) {
        return customerRepository.updateIsAdmin(id, isAdmin);
    }
}
