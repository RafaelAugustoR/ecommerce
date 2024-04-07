package com.rafaelaugusto.ecommerce.services;

import com.rafaelaugusto.ecommerce.domain.entities.Product;
import com.rafaelaugusto.ecommerce.dtos.request.ProductRequestDTO;
import com.rafaelaugusto.ecommerce.dtos.response.ProductResponseDTO;
import com.rafaelaugusto.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO create(ProductRequestDTO request){
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imgUrl(request.getImgUrl())
                .build();

        Product savedProduct = productRepository.save(product);

        return ProductResponseDTO.builder()
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .imgUrl(savedProduct.getImgUrl())
                .build();
    }

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
    public Page<ProductResponseDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(ProductResponseDTO::new);
    }
}
