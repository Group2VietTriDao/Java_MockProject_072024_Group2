package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,Integer> {

}
