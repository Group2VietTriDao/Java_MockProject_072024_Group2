package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.entity.TrainingCatalog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TrainingCatalogService {
    public TrainingCatalog create(TrainingCatalog catalog);
    public TrainingCatalog update(int id, TrainingCatalog catalog);
    public void delete(int id);
    public List<TrainingCatalog> getAll();
    public Optional<TrainingCatalog> getById(int id);
}
