package com.rafaelaugusto.ecommerce.services;

import com.rafaelaugusto.ecommerce.domain.entities.Product;
import com.rafaelaugusto.ecommerce.dtos.response.ProductResponseDTO;
import com.rafaelaugusto.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ProductResponseDTO.builder()
                    .name(product.getName())
                    .description(product.getDescription())
                    .imgUrl(product.getImgUrl())
                    .price(product.getPrice())
                    .build();
        } else {
            throw new RuntimeException("Product not found for id: " + id);
        }
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {
        List<Product> result = productRepository.findAll();
        return result.stream().map(ProductResponseDTO::new).toList();
    }
}
