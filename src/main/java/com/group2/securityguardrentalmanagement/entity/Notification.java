package com.group2.securityguardrentalmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private int notificationId;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
