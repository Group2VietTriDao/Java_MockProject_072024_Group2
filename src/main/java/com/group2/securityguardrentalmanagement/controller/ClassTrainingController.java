package com.group2.securityguardrentalmanagement.controller;

import com.group2.securityguardrentalmanagement.dto.request.ClassTrainingRequest;
import com.group2.securityguardrentalmanagement.dto.request.ClassTrainingUpdateRequest;
import com.group2.securityguardrentalmanagement.entity.ClassTraining;
import com.group2.securityguardrentalmanagement.service.ClassTrainingService;
import com.group2.securityguardrentalmanagement.service.EmployeeTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-training")
public class ClassTrainingController {
    private ClassTrainingService classTrainingService;
    private EmployeeTrainingService employeeTrainingService;
    @Autowired
    public ClassTrainingController(ClassTrainingService classTrainingService, EmployeeTrainingService employeeTrainingService) {
        this.classTrainingService = classTrainingService;
        this.employeeTrainingService = employeeTrainingService;
    }

    @GetMapping("/all-class")
    public ResponseEntity<List<ClassTraining>> getAllClass(){
        List<ClassTraining> list = classTrainingService.getAllClass();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ClassTraining> createClassWithEmployees(@RequestBody ClassTrainingRequest request) {
        ClassTraining classTraining= classTrainingService.createClass(request);
        return new ResponseEntity<>(classTraining, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClassTraining> updateClassTraining(@PathVariable int id, @RequestBody ClassTrainingUpdateRequest request) {
        ClassTraining updatedClassTraining= classTrainingService.updateClass(id, request);
        return new ResponseEntity<>(updatedClassTraining, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClassTraining(@PathVariable int id) {
        classTrainingService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
