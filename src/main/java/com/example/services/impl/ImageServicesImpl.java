package com.example.services.impl;

import com.example.services.inter.ImageServices;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServicesImpl implements ImageServices {

    private final String UPLOAD_DIR="uploads/";
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName= UUID.randomUUID()+"_"+file.getOriginalFilename();
        Path filePath= Paths.get(UPLOAD_DIR+fileName);
        Files.createDirectories(filePath.getParent());
       Files.write(filePath, file.getBytes());
        return "/uploads/"+fileName;
    }
}
