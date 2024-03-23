package com.rafaelaugusto.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String phone;

    @Column(nullable = false, length = 8)
    private LocalDate birthDate;

    @Column(nullable = false, length = 32)
    private String password;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
}