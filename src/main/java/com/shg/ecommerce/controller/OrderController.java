package com.shg.ecommerce.controller;

import com.shg.ecommerce.model.Order;
import com.shg.ecommerce.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(
            @RequestParam Long customerId,
            @RequestParam List<Long> productIds,
            @RequestParam double totalAmount) {
        return orderService.placeOrder(customerId, productIds, totalAmount);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getCustomerOrders(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }
}
