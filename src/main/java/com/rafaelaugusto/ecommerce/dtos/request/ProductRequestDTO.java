package com.rafaelaugusto.ecommerce.dtos.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {

    private String name;

    private String description;

    private String imgUrl;

    private Double price;
}