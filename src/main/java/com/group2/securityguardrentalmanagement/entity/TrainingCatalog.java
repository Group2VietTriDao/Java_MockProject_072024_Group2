package com.group2.securityguardrentalmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "training_catalog")
public class TrainingCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_catalog_id")
    private int trainingCatalogId;
    @Column(name = "training_catalog_name")
    private String trainingCatalogName;
    private String description;
    private String duration;
    private boolean certificate;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "trainingCatalog")
    private Set<EmployeeTraining> employeeTraining = new HashSet<>();
}
