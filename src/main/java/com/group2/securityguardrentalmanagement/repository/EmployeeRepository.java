package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmail(String email);
}
