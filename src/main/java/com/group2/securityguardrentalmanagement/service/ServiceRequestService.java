package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.dto.request.ServiceRequestRequest;
import com.group2.securityguardrentalmanagement.dto.response.ServiceRequestResponse;
import com.group2.securityguardrentalmanagement.entity.ServiceRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceRequestService {
    public ServiceRequest createServiceRequest(ServiceRequestRequest request);
    public List<ServiceRequestResponse> getAllServiceRequest();
}
