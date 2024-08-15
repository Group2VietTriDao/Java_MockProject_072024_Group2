package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.entity.RoleEntity;
import com.group2.securityguardrentalmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<RoleEntity> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
}