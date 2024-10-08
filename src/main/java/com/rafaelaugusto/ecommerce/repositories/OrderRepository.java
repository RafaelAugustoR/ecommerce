package com.rafaelaugusto.ecommerce.repositories;

import com.rafaelaugusto.ecommerce.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
