package com.example.request;

import com.example.entites.Image;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Məhsulun adini daxil edin")
    private String name;
    @NotBlank(message = "Məhsul haqqında məlumat daxil edin")
    private String description;
    @NotBlank(message = "Məhsulun qiymətini  daxil edin")
    private Double price;
    private Integer quantity;
    @NotBlank(message = "Kateqoriya seçin")
    private Long categoryId;
    @NotBlank(message = "Şəhər/Rayon daxil edin")
    private String location;
    private List<Image> files;

}
