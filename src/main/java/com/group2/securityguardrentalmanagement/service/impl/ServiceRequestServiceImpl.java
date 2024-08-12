package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.dto.request.ServiceRequestRequest;
import com.group2.securityguardrentalmanagement.dto.response.ServiceRequestResponse;
import com.group2.securityguardrentalmanagement.entity.ServiceRequest;
import com.group2.securityguardrentalmanagement.entity.UserEntity;
import com.group2.securityguardrentalmanagement.exception.AppException;
import com.group2.securityguardrentalmanagement.exception.ErrorCode;
import com.group2.securityguardrentalmanagement.repository.ServiceRepository;
import com.group2.securityguardrentalmanagement.repository.ServiceRequestRepository;
import com.group2.securityguardrentalmanagement.repository.UserRepository;
import com.group2.securityguardrentalmanagement.service.ServiceRequestService;
import com.group2.securityguardrentalmanagement.entity.Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceRequestServiceImpl implements ServiceRequestService {
    private ServiceRequestRepository serviceRequestRepository;
    private UserRepository userRepository;
    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceRequestServiceImpl(ServiceRequestRepository serviceRequestRepository, UserRepository userRepository, ServiceRepository serviceRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    @Transactional
    public ServiceRequest createServiceRequest(ServiceRequestRequest request) {
        ServiceRequest serviceRequest = new ServiceRequest();

        // Set các giá trị từ request sang entity
        serviceRequest.setStatus(request.getStatus());
        serviceRequest.setNumberOfGuards(request.getNumberOfGuards());
        serviceRequest.setBudget(request.getBudget());
        serviceRequest.setAddress(request.getAddress());
        serviceRequest.setStartDate(request.getStartDate());
        serviceRequest.setEndDate(request.getEndDate());
        serviceRequest.setNote(request.getNote());
        serviceRequest.setCreatedDate(LocalDateTime.now());
        serviceRequest.setUpdatedDate(LocalDateTime.now());


        // Lấy UserEntity và Service dựa trên userId và serviceId, đồng thời kiểm tra tồn tại
        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found with ID: " + request.getServiceId()));
        /*if(request.get)
        String phone = userRepository.findPhoneById(request.getUserId());
        String emai*/
        // Set các quan hệ
        serviceRequest.setUserEntity(userEntity);
        serviceRequest.setService(service);

        // Lưu thực thể ServiceRequest vào cơ sở dữ liệu
        //
        return serviceRequestRepository.save(serviceRequest);
    }


    @Override
    public List<ServiceRequestResponse> getAllServiceRequest() {
        List<ServiceRequest> listServiceRequest = serviceRequestRepository.findAll();
        List<ServiceRequestResponse> requestResponse = new ArrayList<>();
        for(ServiceRequest request : listServiceRequest){
            ServiceRequestResponse response = new ServiceRequestResponse();

            response.setServiceRequestId(request.getServiceRequestId());
            response.setUserId(request.getUserEntity().getUserId());
            response.setUsername(request.getUserEntity().getUsername());
            response.setStatus(request.getStatus());
            response.setNumberOfGuards(request.getNumberOfGuards());
            response.setBudget(request.getBudget());
            response.setStartDate(request.getStartDate());
            response.setEndDate(request.getEndDate());
            response.setServiceName(request.getService().getServiceName());

            requestResponse.add(response);
        }
        return requestResponse;
    }
}
