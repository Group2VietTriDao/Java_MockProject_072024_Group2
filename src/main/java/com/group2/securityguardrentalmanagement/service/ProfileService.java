package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProfileService {
    public List<Profile> saveMultipleProfiles(MultipartFile[] files,int employeeId);
    public Profile saveFileProfile(MultipartFile file,Employee employee);
    public void deleteProfileFile(int profileId);
    public Profile updateProfileFile(int profileId, MultipartFile newFile);
    public void deleteProfiles(List<Integer> profileIds);
}
