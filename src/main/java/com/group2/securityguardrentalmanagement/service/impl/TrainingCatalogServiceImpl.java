package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.entity.TrainingCatalog;
import com.group2.securityguardrentalmanagement.exception.AppException;
import com.group2.securityguardrentalmanagement.exception.ErrorCode;
import com.group2.securityguardrentalmanagement.repository.TrainingCatalogRepository;
import com.group2.securityguardrentalmanagement.service.TrainingCatalogService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingCatalogServiceImpl implements TrainingCatalogService {
    private TrainingCatalogRepository trainingCatalogRepository;

    @Autowired
    public TrainingCatalogServiceImpl(TrainingCatalogRepository trainingCatalogRepository) {
        this.trainingCatalogRepository = trainingCatalogRepository;
    }

    @Override
    @Transactional
    public TrainingCatalog create(TrainingCatalog catalog) {
        catalog.setCreatedDate(LocalDateTime.now());
        return trainingCatalogRepository.save(catalog);
    }

    @Override
    @Transactional
    public TrainingCatalog update(int id, TrainingCatalog catalog) {
        TrainingCatalog trainingCatalog = trainingCatalogRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        trainingCatalog.setTrainingCatalogName(catalog.getTrainingCatalogName());
        trainingCatalog.setCertificate(catalog.isCertificate());
        trainingCatalog.setDuration(catalog.getDuration());
        trainingCatalog.setDescription(catalog.getDescription());
        trainingCatalog.setUpdatedDate(LocalDateTime.now());
        return trainingCatalogRepository.save(trainingCatalog);
    }

    @Override
    @Transactional
    public void delete(int id) {
        trainingCatalogRepository.deleteById(id);
    }

    @Override
    public List<TrainingCatalog> getAll() {
        List<TrainingCatalog> list = trainingCatalogRepository.findAll();
        return list;
    }

    @Override
    public Optional<TrainingCatalog> getById(int id) {
        TrainingCatalog trainingCatalog = trainingCatalogRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        return Optional.ofNullable(trainingCatalog);
    }
}
