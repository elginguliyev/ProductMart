package com.example.controller;

import com.example.services.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {

    @Autowired
    ProductsServices productsServices;

}
