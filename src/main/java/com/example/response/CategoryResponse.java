package com.example.response;

import com.example.request.ProductRequest;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private String name;
    List<ProductResponse> productResponseList;

}
