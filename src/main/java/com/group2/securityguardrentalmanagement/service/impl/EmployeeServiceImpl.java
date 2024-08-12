package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.dto.response.EmployeeResponse;
import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.Profile;
import com.group2.securityguardrentalmanagement.entity.RoleEntity;
import com.group2.securityguardrentalmanagement.entity.UserEntity;
import com.group2.securityguardrentalmanagement.exception.AppException;
import com.group2.securityguardrentalmanagement.exception.ErrorCode;
import com.group2.securityguardrentalmanagement.repository.EmployeeRepository;
import com.group2.securityguardrentalmanagement.repository.RoleRepository;
import com.group2.securityguardrentalmanagement.repository.UserRepository;
import com.group2.securityguardrentalmanagement.service.EmployeeService;
import com.group2.securityguardrentalmanagement.service.FileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private FileService fileService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, FileService fileService, UserRepository userRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.fileService = fileService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Employee createEmployee(String address, LocalDateTime dob, String email,
                                   String gender, String name, String phone,
                                   String status, MultipartFile file,String roleName) {
        try {
            // Kiểm tra xem email đã tồn tại chưa
            // Check if the email already exists
            Optional<Employee> existingEmployee = employeeRepository.findByEmail(email);
            if (existingEmployee.isPresent()) {
                throw new RuntimeException("Employee with email " + email + " already exists.");
            }
            RoleEntity role = roleRepository.findByRoleName(roleName)
                    .orElseThrow(()-> new RuntimeException("Not found role" + roleName));
            UserEntity userEntity = new UserEntity();
            userEntity.setAddress(address);
            userEntity.setDateOfBirth(dob);
            userEntity.setEmail(email);
            userEntity.setGender(gender);
            userEntity.setUsername(name);
            userEntity.setPhoneNumber(phone);
            userEntity.setStatus(status);
            userEntity.setRoleEntity(role);
            userEntity.setCreatedDate(LocalDateTime.now());

            // Lưu UserEntity trước
            // Save UserEntity fist
            userEntity = userRepository.save(userEntity);

            // Tạo và cấu hình đối tượng Employee
            // Create and config Object Employee
            Employee employee = new Employee();
            employee.setUserEntity(userEntity);
            employee.setAddress(address);
            employee.setDateOfBirth(dob);
            employee.setEmail(email);
            employee.setGender(gender);
            employee.setName(name);
            employee.setPhoneNumber(phone);
            employee.setStatus(status);
            employee.setCreatedDate(LocalDateTime.now());

            // Xử lý ảnh
            // image processing
            if (file != null && !file.isEmpty()) {
                String avatar = fileService.saveFile(file);
                employee.setAvatar(avatar);
            }

            // Lưu Employee để có ID
            // Save Employee to get ID
            employee = employeeRepository.save(employee);
            // Gán ID của Employee cho UserEntity và ngược lại
            // assign Employee ID to UserEntity and reverse
            userEntity.setEmployee(employee);
            employee.setUserEntity(userEntity); // Đảm bảo Employee có UserEntity với ID đúng

            userRepository.save(userEntity);
            employeeRepository.save(employee);
            // Lưu employee
            // Save Employee
            return employee;
        } catch (Exception e) {
            throw new RuntimeException("Error creating employee: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Employee updateEmployee(Integer id, String address, LocalDateTime dob, String email, String gender, String name, String phone, String status, MultipartFile file) {
        Employee existEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        UserEntity existUser = userRepository.findByEmployee(existEmployee)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        existUser.setAddress(address);
        existUser.setDateOfBirth(dob);
        existUser.setEmail(email);
        existUser.setGender(gender);
        existUser.setUsername(name);
        existUser.setPhoneNumber(phone);
        existUser.setStatus(status);
        existUser.setUpdatedDate(LocalDateTime.now());

        existUser = userRepository.save(existUser);

        existEmployee.setUserEntity(existUser);
        existEmployee.setAddress(address);
        existEmployee.setDateOfBirth(dob);
        existEmployee.setEmail(email);
        existEmployee.setGender(gender);
        existEmployee.setName(name);
        existEmployee.setPhoneNumber(phone);
        existEmployee.setStatus(status);
        existEmployee.setUpdatedDate(LocalDateTime.now());

        if (file != null && !file.isEmpty()) {
            String avatar = fileService.saveFile(file);
            existEmployee.setAvatar(avatar);
        }

        return employeeRepository.save(existEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Integer id) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            UserEntity userEntity = userRepository.findByEmployee(employee)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            userRepository.delete(userEntity);
            employeeRepository.deleteById(id);
        } catch (AppException e) {
            // Handle application errors, can log or notify the user
            e.printStackTrace(); // Or use logging framework to log errors
            throw e; // Re-throw the exception if necessary
        } catch (Exception e) {
            // Other error handling
            e.printStackTrace();
            throw new RuntimeException("Error occurred while deleting employee", e);
        }
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employee = employeeRepository.findAll();
        List<EmployeeResponse> listresponse = new ArrayList<>();
        for(Employee staff : employee){
            EmployeeResponse response = new EmployeeResponse();
            response.setEmployeeId(staff.getEmployeeId());
            response.setGender(staff.getGender());
            response.setEmail(staff.getEmail());
            response.setAddress(staff.getAddress());
            response.setPhoneNumber(staff.getPhoneNumber());
            response.setStatus(staff.getStatus());
            response.setDateOfBirth(staff.getDateOfBirth());
            response.setName(staff.getName());
            response.setAvatar(staff.getAvatar());

            listresponse.add(response);
        }
        return listresponse;
    }

    @Override
    public EmployeeResponse getEmployeeById(Integer id) {
        Employee staff = employeeRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));

            EmployeeResponse response = new EmployeeResponse();
            response.setEmployeeId(staff.getEmployeeId());
            response.setGender(staff.getGender());
            response.setEmail(staff.getEmail());
            response.setAddress(staff.getAddress());
            response.setPhoneNumber(staff.getPhoneNumber());
            response.setStatus(staff.getStatus());
            response.setDateOfBirth(staff.getDateOfBirth());
            response.setName(staff.getName());
            response.setAvatar(staff.getAvatar());
            List<Profile> lisFile = new ArrayList<>();
            for(Profile tt  : staff.getProfiles()){
                lisFile.add(tt);
            }
            response.setListFile(lisFile);
        return response;
    }
}
