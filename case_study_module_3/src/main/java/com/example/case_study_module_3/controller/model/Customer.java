package com.example.case_study_module_3.controller.model;

import java.util.Objects;

public class Customer {
    private int customerId;
    private String customerName;
    private String customerEmail;
    private String customerPassword;
    private String customerPhone;
    private String customerAddress;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerEmail, String customerPassword, String customerPhone, String customerAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(customerEmail, customer.customerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerEmail);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customerId +
                ", customer_name='" + customerName + '\'' +
                ", customer_email='" + customerEmail + '\'' +
                ", customer_phone='" + customerPhone + '\'' +
                ", customer_address='" + customerAddress + '\'' +
                '}';
    }
}
