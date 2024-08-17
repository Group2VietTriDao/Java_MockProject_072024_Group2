package com.group2.securityguardrentalmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissionResponse {
    private String status;
    private String taskDescription;
    private String address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String employeeName;
    private int contractId;
}
