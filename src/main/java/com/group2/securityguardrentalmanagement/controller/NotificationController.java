package com.group2.securityguardrentalmanagement.controller;

import com.group2.securityguardrentalmanagement.entity.Notification;
import com.group2.securityguardrentalmanagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return ResponseEntity.ok(createdNotification);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Notification>> getNotificationsForUser(@PathVariable("user_id") int userId) {
        List<Notification> notifications = notificationService.getNotificationsForUser(userId);
        return ResponseEntity.ok(notifications);
    }
}
