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
    private ClassTrainingRepository classTrainingRepository;
    private EmployeeTrainingRepository employeeTrainingRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeTrainingServiceImpl(ClassTrainingRepository classTrainingRepository, EmployeeTrainingRepository employeeTrainingRepository, EmployeeRepository employeeRepository) {
        this.classTrainingRepository = classTrainingRepository;
        this.employeeTrainingRepository = employeeTrainingRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public EmployeeTraining createET(int ClassId, List<Integer> employees) {
        ClassTraining existClass = classTrainingRepository.findById(ClassId)
                .orElseThrow(()-> new RuntimeException("not Found Class"));

        EmployeeTraining employeeTraining = new EmployeeTraining();
        employeeTraining.setClassTraining(existClass);
        for(int id : employees){
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Not Found employee"));
            employeeTraining.setEmployee(employee);
        }
        return employeeTrainingRepository.save(employeeTraining);
    }

    @Override
    @Transactional
    public EmployeeTraining updateET(int ET, int EmployeeId, int ClassId) {
        EmployeeTraining employeeTraining = employeeTrainingRepository.findById(ET)
                .orElseThrow(()-> new RuntimeException("Not Found ET"));

        Employee employee = employeeRepository.findById(EmployeeId)
                .orElseThrow(()-> new RuntimeException("Not Found Employee"));

        ClassTraining classTraining = classTrainingRepository.findById(ClassId)
                        .orElseThrow(()-> new RuntimeException("Not Found Class"));
        employeeTraining.setClassTraining(classTraining);
        employeeTraining.setEmployee(employee);

        return employeeTrainingRepository.save(employeeTraining);
    }

    @Override
    @Transactional
    public void deleteET(int ET) {
        EmployeeTraining employeeTraining = employeeTrainingRepository.findById(ET)
                .orElseThrow(()-> new RuntimeException("Not Found ET"));

        employeeTrainingRepository.deleteById(ET);
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
