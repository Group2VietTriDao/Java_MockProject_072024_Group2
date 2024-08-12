package com.group2.securityguardrentalmanagement.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface FileService {
    String saveFile(MultipartFile file);
    Resource loadFile(String fileName);
    public boolean deleteFile(String filePath);
}
