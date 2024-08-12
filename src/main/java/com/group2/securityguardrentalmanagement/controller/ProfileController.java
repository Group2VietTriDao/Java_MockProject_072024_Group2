package com.group2.securityguardrentalmanagement.controller;

import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.Profile;
import com.group2.securityguardrentalmanagement.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private ProfileService profileService;
    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/add/{employeeId}")
    public ResponseEntity<List<Profile>> createProfile(
            @RequestParam("profile") MultipartFile[] files,
            @PathVariable int employeeId) {
        try {
            return ResponseEntity.ok(profileService.saveMultipleProfiles(files, employeeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{profileId}")
    public ResponseEntity<Profile> updateProfileFile(
            @PathVariable int profileId,
            @RequestParam("profile") MultipartFile newFile) {
        try {
            Profile updatedProfile = profileService.updateProfileFile(profileId, newFile);
            return ResponseEntity.ok(updatedProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{profileId}")
    public ResponseEntity<Void> deleteProfileFile(@PathVariable int profileId) {
        try {
            profileService.deleteProfileFile(profileId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProfiles(@RequestParam List<Integer> profileIds) {
        try {
            profileService.deleteProfiles(profileIds);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
