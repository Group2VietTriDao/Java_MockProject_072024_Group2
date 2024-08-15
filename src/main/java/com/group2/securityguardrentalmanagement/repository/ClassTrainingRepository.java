package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.entity.ClassTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassTrainingRepository extends JpaRepository<ClassTraining,Integer> {
}
