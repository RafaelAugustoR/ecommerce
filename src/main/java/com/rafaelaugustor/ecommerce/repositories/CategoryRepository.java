package com.rafaelaugustor.ecommerce.repositories;

import  com.rafaelaugustor.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
