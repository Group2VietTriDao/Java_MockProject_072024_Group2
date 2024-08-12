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
@Table(name = "ServiceRequests")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_request_id")
    private int ServiceRequestId;
    private String status;
    @Column(name = "number_of_guards")
    private int numberOfGuards;
    private double budget;
    private String address;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    private String note;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "delete_at")
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "serviceRequest", fetch = FetchType.LAZY)
    private Set<Contract> contract = new HashSet<>();

}
