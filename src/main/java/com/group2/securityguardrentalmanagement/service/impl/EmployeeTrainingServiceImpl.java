package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.entity.ClassTraining;
import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.EmployeeTraining;
import com.group2.securityguardrentalmanagement.repository.ClassTrainingRepository;
import com.group2.securityguardrentalmanagement.repository.EmployeeRepository;
import com.group2.securityguardrentalmanagement.repository.EmployeeTrainingRepository;
import com.group2.securityguardrentalmanagement.service.EmployeeService;
import com.group2.securityguardrentalmanagement.service.EmployeeTrainingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeeTrainingServiceImpl implements EmployeeTrainingService {
    private EmployeeTrainingRepository employeeTrainingRepository;

    @Autowired
    public EmployeeTrainingServiceImpl(EmployeeTrainingRepository employeeTrainingRepository) {
        this.employeeTrainingRepository = employeeTrainingRepository;
    }

    @Override
    public List<Employee> getAllEmployeeByClass(int ET) {
        List<EmployeeTraining> enrollments = employeeTrainingRepository.findByClassTrainingId(ET);

        // Trích xuất danh sách nhân viên từ danh sách Lớp Traninig
        return enrollments.stream()
                .map(EmployeeTraining::getEmployee)
                .collect(Collectors.toList());
    }
}
