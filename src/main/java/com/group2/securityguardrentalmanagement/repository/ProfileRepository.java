package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {
}
