package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Order;

import java.util.List;

public interface OrderService {
    String placeOrder(Long customerId, List<Long> productIds, double totalAmount);
    List<Order> getOrdersByCustomerId(Long customerId);
}
