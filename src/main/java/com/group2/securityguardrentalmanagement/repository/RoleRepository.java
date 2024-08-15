package com.group2.securityguardrentalmanagement.repository;

<<<<<<< HEAD

=======
>>>>>>> 4d78c45a2805e3c67a3fd102e1f781e0813b8e6c
import com.group2.securityguardrentalmanagement.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleName(String role);
}

