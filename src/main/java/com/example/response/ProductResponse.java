package com.example.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private Long categoryId;

    private String location;

    private List<ImageResponse> imageURLs;

    private List<CommentResponse> comments;

}
