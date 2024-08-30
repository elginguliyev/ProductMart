package com.example.controller;

import com.example.entites.Product;
import com.example.services.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class ProductController {

    @Autowired
    ProductsServices productsServices;

    @GetMapping(path = "product")
    public List<Product> getAllproducts() {
        return productsServices.getAllProducts();
    }


}
