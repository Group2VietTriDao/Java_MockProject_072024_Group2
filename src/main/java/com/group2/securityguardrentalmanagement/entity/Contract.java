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
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private int contractId;
    private String status;
    @Column(name = "number_of_guard")
    private int numberOfGuard;
    private String address;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    private double price;
    private double tax;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    private int rating;
    @Column(name = "feed_back")
    private String feedBack;

    @ManyToOne
    @JoinColumn(name = "serviceRequest_id")
    private ServiceRequest serviceRequest;

    @OneToMany(mappedBy = "contract",fetch = FetchType.LAZY)
    private Set<Report> report = new HashSet<>();
    @OneToMany(mappedBy = "contract",fetch = FetchType.LAZY)
    private Set<FileEntity> fileEntity = new HashSet<>();
}
