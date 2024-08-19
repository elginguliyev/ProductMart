package com.example.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long userId;

    private List<OrderItemDto> orderItems;

    private Double totalPrice;


}
