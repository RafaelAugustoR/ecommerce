package com.rafaelaugusto.ecommerce.rest.controllers;

import com.rafaelaugusto.ecommerce.rest.dtos.request.ProductRequestDTO;
import com.rafaelaugusto.ecommerce.rest.dtos.response.ProductResponseDTO;
import com.rafaelaugusto.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO request) {
        ProductResponseDTO createdProduct = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable @NotNull @Positive Long id, @Valid @RequestBody ProductRequestDTO request) {
        var updatedProduct = productService.update(id, request);
        return ResponseEntity.ok(updatedProduct);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> productById(@PathVariable @NotNull @Positive Long id) {
        var foundProduct = productService.findById(id);
        return ResponseEntity.ok(foundProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @NotNull @Positive Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> allProducts(Pageable pageable) {
        var products = productService.findAll(pageable);
        return ResponseEntity.ok(products);
    }
}
