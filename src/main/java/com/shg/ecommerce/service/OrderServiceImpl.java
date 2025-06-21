package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Customer;
import com.shg.ecommerce.model.Order;
import com.shg.ecommerce.repository.CustomerRepository;
import com.shg.ecommerce.repository.OrderRepository;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String placeOrder(Long customerId, List<Long> productIds, double totalAmount) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            return "Customer not found";
        }

        Customer customer = customerOpt.get();

        Order order = new Order();
        order.setCustomer(customer);
        order.setProductIds(productIds.toString()); // Simple way to store product IDs
        order.setTotalAmount(totalAmount);

        orderRepository.save(order);

        sendOrderConfirmationEmail(customer.getEmail(), productIds, totalAmount);
        return "Order placed successfully";
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findAll()
                .stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .toList();
    }

    private void sendOrderConfirmationEmail(String toEmail, List<Long> productIds, double totalAmount) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Order Confirmation - SHG E-Commerce");
            helper.setText("Your order has been placed successfully.\n" +
                    "Products: " + productIds + "\n" +
                    "Total: ₹" + totalAmount);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
