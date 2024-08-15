package com.group2.securityguardrentalmanagement.controller;

import com.group2.securityguardrentalmanagement.entity.TrainingCatalog;
import com.group2.securityguardrentalmanagement.service.TrainingCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog_training")
public class TrainingCatalogController {
    private TrainingCatalogService trainingCatalogService;

    @Autowired
    public TrainingCatalogController(TrainingCatalogService trainingCatalogService) {
        this.trainingCatalogService = trainingCatalogService;
    }

    @PostMapping("/add")
    public ResponseEntity<TrainingCatalog> createData(@RequestBody TrainingCatalog trainingCatalog){
        TrainingCatalog catalog = trainingCatalogService.create(trainingCatalog);
        return new ResponseEntity<>(catalog, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<TrainingCatalog> updateData(@PathVariable int id, @RequestBody TrainingCatalog trainingCatalog){
        TrainingCatalog catalog = trainingCatalogService.update(id,trainingCatalog);
        return new ResponseEntity<>(catalog, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable int id){
        Optional<TrainingCatalog> catalog = trainingCatalogService.getById(id);
        if (catalog.isPresent()) {
            trainingCatalogService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<TrainingCatalog> getAllRoles() {
        return trainingCatalogService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingCatalog> getRoleById(@PathVariable Integer id) {
        Optional<TrainingCatalog> role = trainingCatalogService.getById(id);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
