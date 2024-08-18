package com.example.dto;

import com.example.entites.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {


    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private Long catagoryId;

    public ProductDto() {
    }

    public ProductDto(String name, String description, double price, int stockQuantity, Long catagoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.catagoryId = catagoryId;
    }
}
