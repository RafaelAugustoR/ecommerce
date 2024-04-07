package com.rafaelaugusto.ecommerce.controllers;

import com.rafaelaugusto.ecommerce.dtos.response.ProductResponseDTO;
import com.rafaelaugusto.ecommerce.services.ProductService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductResponseDTO productById(@PathVariable @NotNull @Positive Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public Page<ProductResponseDTO> allProducts(Pageable pageable) {
        return productService.findAll(pageable);
    }
}
