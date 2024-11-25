package com.folau.datasources.house.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private Double price;
}

