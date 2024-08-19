package com.group2.securityguardrentalmanagement.repository;


import com.group2.securityguardrentalmanagement.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserId(int userId);
}
