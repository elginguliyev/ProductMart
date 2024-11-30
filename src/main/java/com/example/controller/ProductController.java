package com.example.controller;

import com.example.request.ProductRequest;
import com.example.entites.Product;
import com.example.response.ProductResponse;
import com.example.services.inter.ProductsServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class ProductController {


    private final ProductsServices productsServices;

    public ProductController(ProductsServices productsServices) {
        this.productsServices = productsServices;
    }

    @GetMapping(path = "products")
    public ResponseEntity<List<ProductResponse>> getAllproducts() {
        List<ProductResponse> responseList = productsServices.getAllProducts();
        return ResponseEntity.ok(responseList);
    }


    @GetMapping(path = "product/{id}")
    public ResponseEntity<ProductResponse> getByIdProduct(@PathVariable Long id) {
        ProductResponse productResponse = productsServices.getProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping(path = "product/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @RequestBody ProductRequest productDto) {
        productsServices.updateProduct(id, productDto);

        ProductResponse productResponse = getByIdProduct(id).getBody();
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productsServices.deleteProduct(id);
        return ResponseEntity.ok("Məhsul uğurla silindi");
    }

    @PostMapping(path = "add-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addProduct(@ModelAttribute ProductRequest productRequest) throws IOException {

        productsServices.createProduct(productRequest);
        return ResponseEntity.ok("Məhsul uğurla əlavə edildi");
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProductByName(@RequestParam(name = "param") String name) {
        List<ProductResponse> responseList = productsServices.getByName(name);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping(path = "products/search")
    public ResponseEntity<List<ProductResponse>> getByNameAndLocation(@RequestParam(name = "name") String name,
                                                                      @RequestParam(name = "location", required = false) String location,
                                                                          @RequestParam(name = "category" , required = false) String categoryName) {

        if (location == null) {
            location = "";
        }
        if (categoryName == null) {
            categoryName = "";
        }
        List<ProductResponse> productResponses = productsServices.getByNameAndLocationAndCategory(name, location, categoryName);
        return ResponseEntity.ok(productResponses);
    }

}
