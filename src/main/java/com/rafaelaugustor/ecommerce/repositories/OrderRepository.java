package com.rafaelaugustor.ecommerce.repositories;

import com.rafaelaugustor.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
