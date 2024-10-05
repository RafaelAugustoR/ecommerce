package com.rafaelaugusto.ecommerce.services;

import com.rafaelaugusto.ecommerce.domain.entities.Category;
import com.rafaelaugusto.ecommerce.domain.entities.Product;
import com.rafaelaugusto.ecommerce.exceptions.ResourceNotFoundException;
import com.rafaelaugusto.ecommerce.repositories.ProductRepository;
import com.rafaelaugusto.ecommerce.rest.dtos.request.ProductRequestDTO;
import com.rafaelaugusto.ecommerce.rest.dtos.response.CategoryResponseDTO;
import com.rafaelaugusto.ecommerce.rest.dtos.response.ProductMinDTO;
import com.rafaelaugusto.ecommerce.rest.dtos.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO request) {
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


    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for id: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImgUrl(request.getImgUrl());

        Product updatedProduct = productRepository.save(product);

        return ProductResponseDTO.builder()
                .name(updatedProduct.getName())
                .description(updatedProduct.getDescription())
                .price(updatedProduct.getPrice())
                .imgUrl(updatedProduct.getImgUrl())
                .build();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for id: " + id));

        return ProductResponseDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .imgUrl(product.getImgUrl())
                .price(product.getPrice())
                .build();

    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(ProductMinDTO::new);
    }

    private void copyDtoToEntity(ProductResponseDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.getCategories().clear();
        for (CategoryResponseDTO catDto : dto.getCategories()) {
            Category cat = new Category();
            cat.setId(catDto.getId());
            entity.getCategories().add(cat);
        }
    }
}
