package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
