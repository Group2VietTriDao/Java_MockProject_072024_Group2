package com.group2.securityguardrentalmanagement.dto.request;

import com.group2.securityguardrentalmanagement.entity.Contract;
import com.group2.securityguardrentalmanagement.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissionRequest {
    private String description;
    private String status;
    private String taskDescription;
    private String address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double salary;
    private int rating;
    private String feedBack;
    private int employeeId;
    private int contractId;
}
