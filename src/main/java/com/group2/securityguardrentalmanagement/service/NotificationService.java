package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.entity.Notification;

import java.util.List;

public interface NotificationService {
    Notification createNotification(Notification notification);
    List<Notification> getNotificationsForUser(int userId);
}
