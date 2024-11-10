package com.example.dto;

import com.example.entites.Category;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private String name;

    private String description;

    private double price;

    private int Quantity;

    private Long catagoryId;

}
