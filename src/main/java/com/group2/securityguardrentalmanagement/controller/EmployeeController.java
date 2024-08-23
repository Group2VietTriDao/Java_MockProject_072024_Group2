package com.group2.securityguardrentalmanagement.controller;

import com.group2.securityguardrentalmanagement.dto.response.EmployeeResponse;
import com.group2.securityguardrentalmanagement.dto.response.ResponseData;
import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.service.EmployeeService;
import com.group2.securityguardrentalmanagement.service.FileService;
import com.group2.securityguardrentalmanagement.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/staff")
public class EmployeeController {
    private EmployeeService employeeService;
    private FileService fileService;
    private ProfileService profileService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, FileService fileService, ProfileService profileService) {
        this.employeeService = employeeService;
        this.fileService = fileService;
        this.profileService = profileService;
    }

    // sử dụng @ModelAttribute để ánh xạ dữu liệu từ form
    @PostMapping("/add/{employeeId}")
    public ResponseEntity<Employee> createEmployee(
            @RequestParam String address,
            @RequestParam LocalDateTime dob,
            @RequestParam String email,
            @RequestParam String gender,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String status,
            @RequestParam("avatar") MultipartFile file,
            @RequestParam String roleName) {
        try {

            //profileService.saveMultipleCertificates(files, employeeId);
            // Lưu employee vào cơ sở dữ liệu
            Employee newEmployee = employeeService.createEmployee(address,dob,email,gender,name,phone,status,file,roleName);
            return ResponseEntity.ok(newEmployee);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestParam String address,
            @RequestParam LocalDateTime dob,
            @RequestParam String email,
            @RequestParam String gender,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String status,
            @RequestParam("avatar") MultipartFile file) {
        try {
            // Lưu employee vào cơ sở dữ liệu
            Employee newEmployee = employeeService.updateEmployee(id,address,dob,email,gender,name,phone,status,file);
            return ResponseEntity.ok(newEmployee);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/img")
    public ResponseEntity<?> createImg(@RequestParam MultipartFile file){
        ResponseData responseData = new ResponseData();
        String success = fileService.saveFile(file);
        responseData.setDescription(success);
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id){
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build(); // Trả về mã trạng thái 204 No Content nếu xóa thành công
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // Trả về mã trạng thái 404 Not Found nếu không tìm thấy employee
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Trả về mã trạng thái 500 nếu có lỗi khác
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        List<EmployeeResponse> employeeResponses = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeResponses,HttpStatus.OK);
    }
}
