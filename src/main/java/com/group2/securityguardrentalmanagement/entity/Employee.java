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
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;
    private String status;
    private String avatar;
    private String name;
    private String email;
    private String gender;
    private String address;
    @Column(name = "dob")
    private LocalDateTime dateOfBirth;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "delete_at")
    private LocalDateTime deleteAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private Set<Profile> profiles = new HashSet<>();
    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private Set<EmployeeTraining> employeeTraining = new HashSet<>();

}
