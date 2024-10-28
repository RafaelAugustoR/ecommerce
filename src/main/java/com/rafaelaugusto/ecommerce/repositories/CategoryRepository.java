package com.rafaelaugusto.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<com.rafaelaugusto.ecommerce.domain.entities.Category, Long> {

}
