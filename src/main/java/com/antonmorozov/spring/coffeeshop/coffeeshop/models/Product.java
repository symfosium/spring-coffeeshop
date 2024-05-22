package com.antonmorozov.spring.coffeeshop.coffeeshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    @Lob
    private byte[] productPicture; // Tallentaa tuotekuvan base64-muodossa

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "valmistaja_id")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
