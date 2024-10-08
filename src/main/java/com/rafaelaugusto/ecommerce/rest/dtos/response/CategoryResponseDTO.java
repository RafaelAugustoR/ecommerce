package com.rafaelaugusto.ecommerce.rest.dtos.response;

import com.rafaelaugusto.ecommerce.domain.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {

    private Long id;
    private String name;

    public CategoryResponseDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
