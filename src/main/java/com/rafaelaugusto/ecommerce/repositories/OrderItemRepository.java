package com.rafaelaugusto.ecommerce.repositories;

import com.rafaelaugusto.ecommerce.domain.entities.OrderItem;
import com.rafaelaugusto.ecommerce.domain.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
