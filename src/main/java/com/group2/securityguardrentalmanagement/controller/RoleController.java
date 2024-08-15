package com.group2.securityguardrentalmanagement.controller;


import com.group2.securityguardrentalmanagement.entity.RoleEntity;
import com.group2.securityguardrentalmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleEntity> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable Integer id) {
        Optional<RoleEntity> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RoleEntity createRole(@RequestBody RoleEntity role) {
        return roleService.saveRole(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleEntity> updateRole(@PathVariable Integer id, @RequestBody RoleEntity roleDetails) {
        Optional<RoleEntity> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            RoleEntity existingRole = role.get();
            existingRole.setRoleName(roleDetails.getRoleName());
            existingRole.setDescription(roleDetails.getDescription());
            return ResponseEntity.ok(roleService.saveRole(existingRole));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        Optional<RoleEntity> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
