package com.rafaelaugustor.ecommerce.services;

import  com.rafaelaugustor.ecommerce.dto.CategoryDTO;
import  com.rafaelaugustor.ecommerce.entities.Category;
import  com.rafaelaugustor.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> result = repository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).toList();
    }
}
