package com.shg.ecommerce.controller;

import com.shg.ecommerce.model.Notification;
import com.shg.ecommerce.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/seller/{sellerId}")
    public List<Notification> getNotifications(@PathVariable Long sellerId) {
        return notificationService.getNotificationsBySellerId(sellerId);
    }
}
