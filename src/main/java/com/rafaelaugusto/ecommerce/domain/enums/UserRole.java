package com.rafaelaugusto.ecommerce.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {

    ADMIN("Admin"),
    COSTUMER("Costumer");

    private final String role;
}
