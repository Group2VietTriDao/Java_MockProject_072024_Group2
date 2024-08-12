package com.group2.securityguardrentalmanagement.repository;

<<<<<<< HEAD
import com.group2.securityguardrentalmanagement.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleName(String role);
=======
import com.group2.securityguardrentalmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
>>>>>>> bb5f07fcfb54178f34f4df3ecd64ccd3023e5816
}
