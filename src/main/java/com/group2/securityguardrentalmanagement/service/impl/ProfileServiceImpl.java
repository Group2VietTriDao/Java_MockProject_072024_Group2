package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.Profile;
import com.group2.securityguardrentalmanagement.repository.EmployeeRepository;
import com.group2.securityguardrentalmanagement.repository.ProfileRepository;
import com.group2.securityguardrentalmanagement.service.FileService;
import com.group2.securityguardrentalmanagement.service.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Value("${path-upload}")
    private String uploadDir;
    private ProfileRepository profileRepository;
    private EmployeeRepository employeeRepository;
    private FileService fileService;
    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, EmployeeRepository employeeRepository, FileService fileService) {
        this.profileRepository = profileRepository;
        this.employeeRepository = employeeRepository;
        this.fileService = fileService;
    }

    @Override
    public List<Profile> saveMultipleProfiles(MultipartFile[] files,int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        List<Profile> savedProfile = new ArrayList<>();

        for (MultipartFile file : files) {
            Profile certificate = saveFileProfile(file,employee);
            savedProfile.add(certificate);
        }

        return savedProfile;
    }

    @Override
    @Transactional
    public Profile saveFileProfile(MultipartFile file,Employee employee) {
        try{
            // Định nghĩa thư mục lưu trữ
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu file vào thư mục
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Profile profile = new Profile();
            profile.setLink(filePath.toString());
            profile.setEmployee(employee);
            profile.setCreatedDate(LocalDateTime.now());

            profileRepository.save(profile);
            return  profile;

        }catch (IOException e){
            throw new RuntimeException("Failed to save certificate file", e);
        }
    }

    @Override
    @Transactional
    public Profile updateProfileFile(int profileId, MultipartFile newFile) {
        // Tìm profile theo ID
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // Xóa file cũ nếu cần thiết
        String oldFilePath = profile.getLink();
        if (oldFilePath != null && !oldFilePath.isEmpty()) {
            Path oldPath = Paths.get(oldFilePath);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete old file", e);
            }
        }

        // Lưu file mới
        String newFilePath = fileService.saveFile(newFile); // Hàm saveFile() lưu file và trả về đường dẫn
        profile.setLink(newFilePath); // Cập nhật đường dẫn mới
        profile.setUpdatedDate(LocalDateTime.now()); // Cập nhật ngày giờ cập nhật
        // Lưu lại profile với các thuộc tính mới
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void deleteProfileFile(int profileId) {
        Profile existingProfile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // Xóa file khỏi hệ thống tệp
        Path filePath = Paths.get(existingProfile.getLink());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file", e);
        }

        // Xóa thông tin trong database
        profileRepository.delete(existingProfile);
    }

    // Xóa một danh sách các Profile và file liên quan
    @Override
    @Transactional
    public void deleteProfiles(List<Integer> profileIds) {
        for (Integer profileId : profileIds) {
            Profile profile = profileRepository.findById(profileId)
                    .orElseThrow(() -> new RuntimeException("Profile not found"));

            // Xóa file khỏi hệ thống file
            String filePath = profile.getLink();
            if (filePath != null && !filePath.isEmpty()) {
                Path path = Paths.get(filePath);
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to delete file: " + filePath, e);
                }
            }

            // Xóa profile khỏi cơ sở dữ liệu
            profileRepository.delete(profile);
        }
    }

}
