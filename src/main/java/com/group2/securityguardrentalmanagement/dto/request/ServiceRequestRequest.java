package com.group2.securityguardrentalmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequestRequest {
    private int userId;
    private int serviceId;
    private String status;
    private int numberOfGuards;
    private double budget;
    private String phone;
    private String email;
    private String address;
    private String note;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deleteAt;
}
