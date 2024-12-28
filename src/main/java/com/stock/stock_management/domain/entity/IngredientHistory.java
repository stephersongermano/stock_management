package com.stock.stock_management.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.stock.stock_management.application.dto.IngredientHistoryRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ingredient_history")
public class IngredientHistory {

    @Id
    @GeneratedValue(generator = "ingredient_history_id_seq_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ingredient_history_id_seq_generator", sequenceName = "ingredient_history_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "stock_entry_id")
    private StockEntry stockEntry;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public IngredientHistory(IngredientHistoryRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
        this.brand = request.getBrand();
    }

    public IngredientHistory(String name, BigDecimal price, Integer quantity, String brand) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
    }

    @PrePersist
    private void setcreatedAt() {
        this.created_at = LocalDateTime.now();
    }

}
