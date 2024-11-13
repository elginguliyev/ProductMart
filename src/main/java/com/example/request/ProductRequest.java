package com.example.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private Long categoryId;

    private List<MultipartFile> files;

}
