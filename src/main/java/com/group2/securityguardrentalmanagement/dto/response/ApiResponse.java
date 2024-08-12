package com.group2.securityguardrentalmanagement.dto.response;

import lombok.Data;

@Data
public class ApiResponse <T>{
    private int code = 200;
    private String message;
    private T result;
}
