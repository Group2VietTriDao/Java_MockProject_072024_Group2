package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.dto.response.EmployeeResponse;
import com.group2.securityguardrentalmanagement.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
@Service
public interface EmployeeService {
    public Employee createEmployee(String address, LocalDateTime dob, String email, String gender, String name, String phone, String status, MultipartFile file, String role);
    public Employee updateEmployee(Integer id, String address, LocalDateTime dob, String email, String gender, String name, String phone, String status, MultipartFile file);
    public void deleteEmployee(Integer id);
    public List<EmployeeResponse> getAllEmployee();
    public EmployeeResponse getEmployeeById(Integer id);
}
