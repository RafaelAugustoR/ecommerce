package com.rafaelaugusto.ecommerce.rest.controllers;

import com.rafaelaugusto.ecommerce.rest.dtos.response.UserResponseDTO;
import com.rafaelaugusto.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserResponseDTO> getMe() {
        UserResponseDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }
}
