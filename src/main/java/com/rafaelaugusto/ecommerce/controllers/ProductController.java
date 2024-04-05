package com.rafaelaugusto.ecommerce.controllers;

import com.rafaelaugusto.ecommerce.dtos.response.ProductResponseDTO;
import com.rafaelaugusto.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public ProductResponseDTO productById(@PathVariable Long id){
        return productService.findById(id);
    }
}
