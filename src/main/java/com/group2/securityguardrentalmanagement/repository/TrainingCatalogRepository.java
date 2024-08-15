package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.entity.TrainingCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingCatalogRepository extends JpaRepository<TrainingCatalog, Integer> {
}
