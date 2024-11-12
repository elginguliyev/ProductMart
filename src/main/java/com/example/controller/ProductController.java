package com.example.controller;

import com.example.dto.ProductDto;
import com.example.entites.Product;
import com.example.services.inter.ProductsServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class ProductController {


    private final ProductsServices productsServices;

    public ProductController(ProductsServices productsServices) {
        this.productsServices = productsServices;
    }

    @GetMapping(path = "products")
    public ResponseEntity<List<ProductDto>> getAllproducts() {
        List<ProductDto> productDtoList = productsServices.getAllProducts();
        return ResponseEntity.ok(productDtoList);
    }


    @GetMapping(path = "{id}/product")
    public ResponseEntity<ProductDto> getByIdProduct(@PathVariable Long id) {
        ProductDto productDto = productsServices.getProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping(path = "{id}/product")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDto productDto) {
        productsServices.updateProduct(id, productDto);

        ProductDto productDto1 = getByIdProduct(id).getBody();
        return ResponseEntity.ok(productDto1);
    }

    @DeleteMapping("{id}/product")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productsServices.deleteProduct(id);
        return ResponseEntity.ok("Məhsul uğurla silindiş");
    }

    @PostMapping(path = "add/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
        Product product = productsServices.createProduct(productDto);

        return ResponseEntity.ok("Məhsul uğurla əlavə edildiş");
    }

    @GetMapping(path = "products/search")
    public ResponseEntity<List<ProductDto>> getProductByName(@RequestParam(name = "param") String name) {
        List<ProductDto> productDtoList = productsServices.getByName(name);
        return ResponseEntity.ok(productDtoList);
    }

}
