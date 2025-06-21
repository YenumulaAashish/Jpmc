package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Notification;
import java.util.List;

public interface NotificationService {
    List<Notification> getNotificationsBySellerId(Long sellerId);
}
