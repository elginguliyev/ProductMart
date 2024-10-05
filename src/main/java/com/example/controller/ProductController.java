package com.example.controller;

import com.example.entites.Product;
import com.example.services.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ProductController {

    @Autowired
    ProductsServices productsServices;

    @GetMapping(path = "/all/product")
    public ResponseEntity<List<Product>> getAllproducts() {
        List<Product> productList = productsServices.getAllProducts();
        return ResponseEntity.ok(productList);
    }


    @GetMapping(path = "/{id}/product")
    public ResponseEntity<Product> getByIdProduct(@PathVariable Long id) {
        Product product = productsServices.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        productsServices.deleteProduct(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully");
    }

}
