package com.rafaelaugusto.ecommerce.rest.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {


    @NotBlank(message = "name cannot be null")
    @Length(min = 3, max = 100, message = "name needs to have between 5 and 100 characters.")
    private String name;

    @NotBlank(message = "description cannot be null")
    @Length(min = 10, message = "description needs to have minimum 10 characters.")
    private String description;

    @NotBlank(message = "image cannot be null")
    private String imgUrl;

    @Positive(message = "price needs to be positive")
    private Double price;
}
