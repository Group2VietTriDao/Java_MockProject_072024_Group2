package com.group2.securityguardrentalmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private int employee_id;
    private int user_id;
    private String title;
    private String message;

    // Getters and Setters
}
