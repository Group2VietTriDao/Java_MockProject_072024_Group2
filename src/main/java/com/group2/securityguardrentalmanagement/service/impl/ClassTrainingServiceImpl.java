package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.entity.ClassTraining;
import com.group2.securityguardrentalmanagement.service.ClassTrainingService;

import java.util.List;
import java.util.Optional;

public class ClassTrainingServiceImpl implements ClassTrainingService {
    @Override
    public ClassTraining createClass(ClassTraining classTraining, int employeeId) {
        return null;
    }

    @Override
    public ClassTraining updateClass(int id, ClassTraining classTraining, int employeeId) {
        return null;
    }

    @Override
    public void deleteClass(int id) {

    }

    @Override
    public List<ClassTraining> getAllClass() {
        return List.of();
    }

    @Override
    public Optional<ClassTraining> getClassbyId(int id) {
        return Optional.empty();
    }
}
