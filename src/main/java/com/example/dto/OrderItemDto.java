package com.example.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;

    private int quantity;

    private double price;

}
