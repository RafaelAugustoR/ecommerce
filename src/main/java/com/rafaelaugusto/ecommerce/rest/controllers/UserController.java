package com.rafaelaugusto.ecommerce.rest.controllers;

import com.rafaelaugusto.ecommerce.rest.dtos.response.UserResponseDTO;
import com.rafaelaugusto.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMe(){
        return ResponseEntity.ok(userService.getMe());
    }
}
