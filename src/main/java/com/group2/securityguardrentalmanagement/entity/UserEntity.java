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
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    private String username;
    private String password;
    private String status;
    private String avatar;
    private String email;
    private String gender;
    @Column(name = "dob")
    private LocalDateTime dateOfBirth;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "delete_at")
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ServiceRequest> serviceRequest = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
