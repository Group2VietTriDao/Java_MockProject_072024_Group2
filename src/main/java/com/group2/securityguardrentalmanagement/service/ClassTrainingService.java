package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.entity.ClassTraining;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClassTrainingService {
    public ClassTraining createClass(ClassTraining classTraining, int employeeId);
    public ClassTraining updateClass(int id, ClassTraining classTraining, int employeeId);
    public void deleteClass(int id);
    public List<ClassTraining> getAllClass();
    public Optional<ClassTraining> getClassbyId(int id);
}
