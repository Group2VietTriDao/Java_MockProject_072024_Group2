package com.group2.securityguardrentalmanagement.dto.response;

import com.group2.securityguardrentalmanagement.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private int employeeId;
    private String status;
    private String avatar;
    private String name;
    private String email;
    private String gender;
    private String address;
    private LocalDateTime dateOfBirth;
    private String phoneNumber;
    private List<Profile> listFile;
}
