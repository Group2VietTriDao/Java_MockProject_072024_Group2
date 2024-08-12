package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    @Query("SELECT u.phoneNumber FROM UserEntity u WHERE u.userId = :userId")
    String findPhoneById(@Param("userId") int userId);

    @Query("SELECT u.email FROM UserEntity u WHERE u.userId = :userId")
    String findEmailById(@Param("userId") int userId);

    Optional<UserEntity> findByEmployee(Employee employee);
}
