package com.rafaelaugusto.ecommerce.rest.dtos.response;

import com.rafaelaugusto.ecommerce.domain.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {

    private Long id;
    private Instant moment;

    public PaymentResponseDTO(Payment entity) {
        id = entity.getId();
        moment = entity.getMoment();
    }
}
