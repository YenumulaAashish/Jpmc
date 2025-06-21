package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Customer;

import java.util.List;
public interface CustomerService {
    String registerCustomer(Customer customer);
    boolean verifyOtp(String email, String otp, String password); // ✅ Add password
    Customer loginCustomer(String email, String password);
    List<Customer> getAllCustomers();
    Customer getCustomerByEmail(String email);
    Customer updateCustomer(Long id, Customer updatedCustomer);
    String deleteCustomer(Long id);
    List<Customer> searchCustomerByName(String name);
}
