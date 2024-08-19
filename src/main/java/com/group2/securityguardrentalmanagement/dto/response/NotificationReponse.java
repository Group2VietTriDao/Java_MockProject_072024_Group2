package com.group2.securityguardrentalmanagement.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationReponse {
    private int notification_id;
    private int employee_id;
    private int user_id;
    private String title;
    private String message;
    private LocalDateTime createdDate;
}
