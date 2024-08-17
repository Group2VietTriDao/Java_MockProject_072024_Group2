package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.EmployeeTraining;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeTrainingService {
    public EmployeeTraining createET(int ClassId, List<Integer> employees);
    public EmployeeTraining updateET(int ET,int EmployeeId, int ClassId);
    public void deleteET(int ET);
    public List<Employee> getAllEmployeeByClass(int ET);
}
