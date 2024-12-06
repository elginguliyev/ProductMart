package com.example.controller;

import com.example.services.inter.ImageServices;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(path = "/api/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FileController {

    private final ImageServices imageServices;

    @PostMapping(path = "uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = imageServices.upload(file);
        return fileName;
    }

    @GetMapping("download/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveRawFile(@PathVariable String filename) throws Exception {

        Path p = load(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(new UrlResource(p.toUri()));
    }

    public Path load(String filename) {
        return Paths.get("uploads").resolve(filename).normalize();

    }
}
