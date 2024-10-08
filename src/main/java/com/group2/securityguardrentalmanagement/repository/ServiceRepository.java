package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
