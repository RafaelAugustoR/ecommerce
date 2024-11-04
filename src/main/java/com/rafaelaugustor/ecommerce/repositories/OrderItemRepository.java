package com.rafaelaugustor.ecommerce.repositories;

import com.rafaelaugustor.ecommerce.entities.OrderItem;
import com.rafaelaugustor.ecommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
