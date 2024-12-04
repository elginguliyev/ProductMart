package com.example.services.impl;

import com.example.services.inter.ImageServices;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageServicesImpl implements ImageServices {

    private final String UPLOAD_DIR = "uploads/";

    @Override
    public String upload(MultipartFile file) throws IOException {
        String newFileName = null;
        try {
            InputStream stream = file.getInputStream();

            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            String fileName = file.getOriginalFilename();
            int lastInt = fileName.lastIndexOf(".");
            String subName = fileName.substring(0, lastInt);
            newFileName = fileName.replace(subName, uuidStr);
            Path uploadPath = Paths.get("uploads");
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(stream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception ex){
            ex.printStackTrace();
        }


//        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//        Path filePath = Paths.get(UPLOAD_DIR + fileName);
//        Files.createDirectories(filePath.getParent());
//        Files.write(filePath, file.getBytes());
        return newFileName;
    }
}
