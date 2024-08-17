package com.group2.securityguardrentalmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassTrainingRequest {
    private String className;
    private String status;
    private String description;
    private String location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Integer> employeeIds;
}
