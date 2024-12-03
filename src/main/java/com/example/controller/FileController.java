package com.example.controller;

import com.example.services.inter.ImageServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/")
@RequiredArgsConstructor
public class FileController {

    private final ImageServices imageServices;

    @PostMapping(path = "uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = null;
        try {
            fileName = imageServices.uploadFile(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileName;
    }
}
