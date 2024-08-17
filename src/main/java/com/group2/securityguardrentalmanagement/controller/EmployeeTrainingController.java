package com.group2.securityguardrentalmanagement.controller;

import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.service.EmployeeService;
import com.group2.securityguardrentalmanagement.service.EmployeeTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-training")
public class EmployeeTrainingController {
    private EmployeeTrainingService employeeTrainingService;
    @Autowired
    public EmployeeTrainingController(EmployeeTrainingService employeeTrainingService) {
        this.employeeTrainingService = employeeTrainingService;
    }

    @GetMapping("/all-employee/{ET}")
    public ResponseEntity<List<Employee>> getAllEmployeeByET(@PathVariable int ET){
        List<Employee> list = employeeTrainingService.getAllEmployeeByClass(ET);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
