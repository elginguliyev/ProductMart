package com.example.dto;

import com.example.entites.Category;
import lombok.*;

import java.io.DataInput;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private Long catagoryId;

}
