package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileServiceImpl implements FileService {
    @Value("${path-upload}")
    private String uploadDir;
    private Path root;

    // tạo là folder root đó khi nó không tồn tại
    public void inIt(){
            try {
                root = Paths.get(uploadDir);
                if(Files.notExists(root)) {// nếu file root chưa tồn tại
                    Files.createDirectories(root);// thì tạo folder
                }
            } catch (IOException e) {
                System.out.println("Error create folder root: " + e.getMessage());
            }

    }

    @Override
    public String saveFile(MultipartFile file) {// xuất ra String để lưu path xuống cơ sở dữ liệu, để boolean cx dc
        try {
            inIt();
            Path filePath = root.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);// nếu file đã tồn tại thì ghi đè
            return filePath.toString();
            //return true;
        }catch(Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
            return "Error saving file: " + e.getMessage();
            //return false;
        }
    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            inIt();
            Path file = root.resolve(fileName);//resolve tương ứng với "\"
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new RuntimeException("Could not read file: " + fileName);
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + fileName, e);
        }

    }
    public boolean deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.deleteIfExists(path);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
