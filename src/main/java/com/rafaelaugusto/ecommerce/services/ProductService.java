package com.rafaelaugusto.ecommerce.services;

import com.rafaelaugusto.ecommerce.domain.entities.Category;
import com.rafaelaugusto.ecommerce.domain.entities.Product;
import com.rafaelaugusto.ecommerce.exceptions.DatabaseException;
import com.rafaelaugusto.ecommerce.exceptions.ResourceNotFoundException;
import com.rafaelaugusto.ecommerce.repositories.ProductRepository;
import com.rafaelaugusto.ecommerce.rest.dtos.response.CategoryResponseDTO;
import com.rafaelaugusto.ecommerce.rest.dtos.response.ProductMinDTO;
import com.rafaelaugusto.ecommerce.rest.dtos.response.ProductResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductResponseDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<Product> result = repository.searchByName(name, pageable);
        return result.map(x -> new ProductMinDTO(x));
    }

    @Transactional
    public ProductResponseDTO insert(ProductResponseDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductResponseDTO(entity);
    }

    @Transactional
    public ProductResponseDTO update(Long id, ProductResponseDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductResponseDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
    	if (!repository.existsById(id)) {
    		throw new ResourceNotFoundException("Recurso não encontrado");
    	}
    	try {
            repository.deleteById(id);    		
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
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
