package com.example.services.inter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ImageServices {

    String upload(MultipartFile  file) throws IOException;
}
