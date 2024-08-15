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

<<<<<<< HEAD
=======
    // Retrieve all roles
>>>>>>> 4d78c45a2805e3c67a3fd102e1f781e0813b8e6c
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

<<<<<<< HEAD
=======
    // Retrieve a role by ID
>>>>>>> 4d78c45a2805e3c67a3fd102e1f781e0813b8e6c
    public Optional<RoleEntity> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

<<<<<<< HEAD
=======
    // Save a role
>>>>>>> 4d78c45a2805e3c67a3fd102e1f781e0813b8e6c
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    // Delete a role by ID
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
}
