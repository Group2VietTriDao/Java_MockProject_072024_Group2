package com.group2.securityguardrentalmanagement.controller;

import com.group2.securityguardrentalmanagement.dto.request.ServiceRequestRequest;
import com.group2.securityguardrentalmanagement.dto.response.ServiceRequestResponse;
import com.group2.securityguardrentalmanagement.entity.ServiceRequest;
import com.group2.securityguardrentalmanagement.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-request")
public class ServiceRequestController {
    private ServiceRequestService serviceRequestService;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceRequestResponse>> getAll(){
        List<ServiceRequestResponse> list = serviceRequestService.getAllServiceRequest();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceRequest> create(@RequestBody ServiceRequestRequest request){
        ServiceRequest serviceRequest = serviceRequestService.createServiceRequest(request);
        return new ResponseEntity<>(serviceRequest, HttpStatus.OK);
    }
}
