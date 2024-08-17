package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.dto.request.ClassTrainingRequest;
import com.group2.securityguardrentalmanagement.dto.request.ClassTrainingUpdateRequest;
import com.group2.securityguardrentalmanagement.entity.ClassTraining;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClassTrainingService {
    public ClassTraining createClass(ClassTrainingRequest request);
    public ClassTraining updateClass(int id, ClassTrainingUpdateRequest request);
    public void deleteClass(int id);
    public List<ClassTraining> getAllClass();
    public Optional<ClassTraining> getClassById(int id);
}
