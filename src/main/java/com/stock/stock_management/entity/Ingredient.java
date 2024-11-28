package com.stock.stock_management.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "ingredient_id_seq_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ingredient_id_seq_generator", sequenceName = "ingredient_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    public Ingredient(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
