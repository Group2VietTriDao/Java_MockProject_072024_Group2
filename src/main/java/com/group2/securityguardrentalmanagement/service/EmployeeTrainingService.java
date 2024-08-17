package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.EmployeeTraining;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeTrainingService {
    public List<Employee> getAllEmployeeByClass(int ET);
}
