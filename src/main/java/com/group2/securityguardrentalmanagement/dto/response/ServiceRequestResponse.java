package com.group2.securityguardrentalmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequestResponse {
    private int serviceRequestId;
    private int userId;
    private String username;
    private String status;
    private int numberOfGuards;
    private double budget;
    private String serviceName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
