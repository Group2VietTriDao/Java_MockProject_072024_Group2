package com.group2.securityguardrentalmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role_name")
    private String roleName;
    private String description;

    @OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserEntity> userEntity = new HashSet<>();

}
