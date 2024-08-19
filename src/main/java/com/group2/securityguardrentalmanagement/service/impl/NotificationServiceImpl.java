package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.entity.Notification;
import com.group2.securityguardrentalmanagement.repository.NotificationRepository;
import com.group2.securityguardrentalmanagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification notification) {
        notification.setCreatedDate(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsForUser(int userId) {
        return notificationRepository.findByUserId(userId);
    }
}
