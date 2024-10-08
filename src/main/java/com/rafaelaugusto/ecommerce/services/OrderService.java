package com.rafaelaugusto.ecommerce.services;

import java.time.Instant;

import com.rafaelaugusto.ecommerce.domain.entities.Order;
import com.rafaelaugusto.ecommerce.domain.entities.OrderItem;
import com.rafaelaugusto.ecommerce.domain.entities.Product;
import com.rafaelaugusto.ecommerce.domain.entities.User;
import com.rafaelaugusto.ecommerce.domain.enums.OrderStatus;
import com.rafaelaugusto.ecommerce.exceptions.ResourceNotFoundException;
import com.rafaelaugusto.ecommerce.repositories.OrderRepository;
import com.rafaelaugusto.ecommerce.repositories.ProductRepository;
import com.rafaelaugusto.ecommerce.rest.dtos.response.OrderItemResponseDTO;
import com.rafaelaugusto.ecommerce.rest.dtos.response.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderResponseDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderResponseDTO insert(OrderResponseDTO dto) {

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemResponseDTO itemDto : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order, product, product.getPrice(), itemDto.getQuantity());
            order.getItems().add(item);
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderResponseDTO(order);
    }
}
