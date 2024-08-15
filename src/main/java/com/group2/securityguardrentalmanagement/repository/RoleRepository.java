package com.group2.securityguardrentalmanagement.repository;


import com.group2.securityguardrentalmanagement.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleName(String role);
}
