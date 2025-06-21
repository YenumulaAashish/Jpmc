package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Notification;
import com.shg.ecommerce.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getNotificationsBySellerId(Long sellerId) {
        return notificationRepository.findBySellerId(sellerId);
    }
}
