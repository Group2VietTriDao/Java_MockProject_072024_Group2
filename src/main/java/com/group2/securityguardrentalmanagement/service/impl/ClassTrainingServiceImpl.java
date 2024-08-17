package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.dto.request.ClassTrainingRequest;
import com.group2.securityguardrentalmanagement.dto.request.ClassTrainingUpdateRequest;
import com.group2.securityguardrentalmanagement.entity.ClassTraining;
import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.EmployeeTraining;
import com.group2.securityguardrentalmanagement.repository.ClassTrainingRepository;
import com.group2.securityguardrentalmanagement.repository.EmployeeRepository;
import com.group2.securityguardrentalmanagement.repository.EmployeeTrainingRepository;
import com.group2.securityguardrentalmanagement.service.ClassTrainingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClassTrainingServiceImpl implements ClassTrainingService {
    private ClassTrainingRepository classTrainingRepository;
    private EmployeeRepository employeeRepository;
    private EmployeeTrainingRepository employeeTrainingRepository;

    @Autowired
    public ClassTrainingServiceImpl(ClassTrainingRepository classTrainingRepository, EmployeeRepository employeeRepository, EmployeeTrainingRepository employeeTrainingRepository) {
        this.classTrainingRepository = classTrainingRepository;
        this.employeeRepository = employeeRepository;
        this.employeeTrainingRepository = employeeTrainingRepository;
    }

    @Override
    @Transactional
    public ClassTraining createClass(ClassTrainingRequest request) {
        // Tạo lớp học mới
        ClassTraining classTraining = new ClassTraining();
        classTraining.setClassName(request.getClassName());
        classTraining.setDescription(request.getDescription());
        classTraining.setLocation(request.getLocation());
        classTraining.setStartDate(request.getStartDate());
        classTraining.setEndDate(request.getEndDate());

        classTrainingRepository.save(classTraining);
        // thêm nhân viên vào
        for(int employeeId : request.getEmployeeIds()){
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(()-> new RuntimeException("Not Found Employee"));
            EmployeeTraining employeeTraining = new EmployeeTraining();

            employeeTraining.setEmployee(employee);
            employeeTraining.setClassTraining(classTraining);
            employeeTraining.setStatus("Active");
            employeeTrainingRepository.save(employeeTraining);
        }
        return classTraining;
    }

    @Override
    @Transactional
    public ClassTraining updateClass(int id, ClassTrainingUpdateRequest request) {
        ClassTraining classTraining = classTrainingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Not found Class"));

        classTraining.setClassName(request.getClassName());
        classTraining.setDescription(request.getDescription());
        classTraining.setStatus(request.getStatus());
        classTraining.setLocation(request.getLocation());
        classTraining.setStartDate(request.getStartDate());
        classTraining.setEndDate(request.getEndDate());

        // Xóa nhân  viên đã chọn
        if(request.getEmployeeIdsToRemove() != null){
            List<EmployeeTraining> employeeTrainingsToRemove = employeeTrainingRepository.findByClassTrainingIdAndEmployeeId(id, request.getEmployeeIdsToRemove());
            employeeTrainingRepository.deleteAll(employeeTrainingsToRemove);
        }
        // Thêm mới nhân viên lại hoặc không thêm
        if(request.getEmployeeIdsToAdd() != null) {
            for (int employeeId : request.getEmployeeIdsToAdd()) {
                Employee employee = employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new RuntimeException("Not Found Employee"));
                EmployeeTraining employeeTraining = new EmployeeTraining();

                employeeTraining.setEmployee(employee);
                employeeTraining.setClassTraining(classTraining);
                employeeTraining.setStatus("Active");
                employeeTrainingRepository.save(employeeTraining);
            }
        }
        return classTrainingRepository.save(classTraining);
    }

    @Override
    @Transactional
    public void deleteClass(int id) {
        // Xóa tất cả các liên kết nhân viên trước khi xóa lớp học
        employeeTrainingRepository.deleteByClassTrainingId(id);
        // Xóa lớp học
        classTrainingRepository.deleteById(id);

    }

    @Override
    public List<ClassTraining> getAllClass() {
        return classTrainingRepository.findAll();
    }

    @Override
    public Optional<ClassTraining> getClassById(int id) {
        Optional<ClassTraining> classT = classTrainingRepository.findById(id);
        return classT;
    }
}
