package com.group2.securityguardrentalmanagement.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private int employeeId;
    private String status;
    private String avatar;
    private String name;
    private String email;
    private String gender;
    private String address;
    private LocalDateTime dateOfBirth;
    private String phoneNumber;
    private int UserId;
    private String[] listFile;
}
