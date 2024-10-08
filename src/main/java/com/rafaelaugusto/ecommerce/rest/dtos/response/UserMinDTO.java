package com.rafaelaugusto.ecommerce.rest.dtos.response;

import com.rafaelaugusto.ecommerce.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserMinDTO {

    private Long id;
    private String name;

    public UserMinDTO(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
