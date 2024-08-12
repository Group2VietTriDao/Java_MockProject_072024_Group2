package com.group2.securityguardrentalmanagement.dto.response;

import lombok.Data;

@Data
public class ResponseData {
    private int status = 200;
    private String description;
    private Object data;
}
