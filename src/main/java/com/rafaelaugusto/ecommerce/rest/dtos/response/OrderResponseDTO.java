package com.rafaelaugusto.ecommerce.rest.dtos.response;

import com.rafaelaugusto.ecommerce.domain.entities.Order;
import com.rafaelaugusto.ecommerce.domain.entities.OrderItem;
import com.rafaelaugusto.ecommerce.domain.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponseDTO {

	private Long id;
	private Instant moment;
	private OrderStatus status;
	private UserMinDTO user;
	private PaymentResponseDTO payment;
	private String imgUrl;
	
	@NotEmpty(message = "Deve ter pelo menos um item")
	private List<OrderItemResponseDTO> items = new ArrayList<>();
	
	public OrderResponseDTO(Order entity) {
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.status = entity.getStatus();
		this.user = new UserMinDTO(entity.getClient());
		this.payment = (entity.getPayment() == null) ? null : new PaymentResponseDTO(entity.getPayment());
		this.imgUrl = entity.getProducts().getFirst().getImgUrl();
		for (OrderItem item : entity.getItems()) {
			OrderItemResponseDTO itemDto = new OrderItemResponseDTO(item);
			items.add(itemDto);
		}
 	}

    public Double getTotal() {
		double sum = 0.0;
		for (OrderItemResponseDTO item : items) {
			sum += item.getSubTotal();
		}
		return sum;
	}
}
