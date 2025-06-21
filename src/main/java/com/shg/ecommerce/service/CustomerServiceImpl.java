package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Customer;
import com.shg.ecommerce.repository.CustomerRepository;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, String> otpMap = new HashMap<>();

    @Override
    public String registerCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            return "Customer already exists with this email";
        }

        String otp = generateOtp();
        otpMap.put(customer.getEmail(), otp);

        sendOtpEmail(customer.getEmail(), otp);
        customerRepository.save(customer);
        return "Customer registered and OTP sent to email";
    }

    // ✅ Updated to handle password
    @Override
    public boolean verifyOtp(String email, String otp, String password) {
        String storedOtp = otpMap.get(email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            Customer customer = customerRepository.findByEmail(email);
            if (customer != null) {
                customer.setPassword(password);
                otpMap.remove(email); // OTP is used
                customerRepository.save(customer);
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer loginCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setName(updatedCustomer.getName());
            customer.setPhoneNo(updatedCustomer.getPhoneNo());
            customer.setGender(updatedCustomer.getGender());
            customer.setDateOfBirth(updatedCustomer.getDateOfBirth());
            customer.setLocation(updatedCustomer.getLocation());
            customer.setPassword(updatedCustomer.getPassword());
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public String deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return "Customer deleted successfully";
        }
        return "Customer not found";
    }

    @Override
    public List<Customer> searchCustomerByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }

    // Helper methods

    private String generateOtp() {
        return String.valueOf(new Random().nextInt(900000) + 100000); // 6-digit OTP
    }

    private void sendOtpEmail(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Your OTP for Login - SHG E-Commerce");
            helper.setText("Your OTP is: " + otp);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
