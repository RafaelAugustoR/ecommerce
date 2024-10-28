package com.rafaelaugusto.ecommerce.services;

import com.rafaelaugusto.ecommerce.domain.entities.Category;
import com.rafaelaugusto.ecommerce.repositories.CategoryRepository;
import com.rafaelaugusto.ecommerce.rest.dtos.response.CategoryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll() {
        List<Category> result = repository.findAll();
        return result.stream().map(x -> new CategoryResponseDTO(x)).toList();
    }
}
