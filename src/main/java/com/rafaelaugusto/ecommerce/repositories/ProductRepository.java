package com.rafaelaugusto.ecommerce.repositories;

import com.rafaelaugusto.ecommerce.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
