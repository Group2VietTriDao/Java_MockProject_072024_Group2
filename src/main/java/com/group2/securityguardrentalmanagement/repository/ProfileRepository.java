package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
}
