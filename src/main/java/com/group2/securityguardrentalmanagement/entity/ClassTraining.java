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
@Table(name = "class_trainings")
public class ClassTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_training_id")
    private int classTrainingId;
    private String className;
    private String status;
    private String description;
    private String location;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "delete_at")
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "trainingCatalog_id")
    private TrainingCatalog trainingCatalog;

    @OneToMany(mappedBy = "classTraining",fetch = FetchType.LAZY)
    private Set<EmployeeTraining> employeeTraining = new HashSet<>();
}
