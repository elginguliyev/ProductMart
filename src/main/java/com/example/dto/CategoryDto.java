package com.example.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String name;
    List<ProductDto> productDtoList;

}
