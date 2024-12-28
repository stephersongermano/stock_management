package com.stock.stock_management.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.stock.stock_management.application.dto.IngredientRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ingredient")
@SQLDelete(sql = "UPDATE ingredient SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "ingredient_id_seq_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ingredient_id_seq_generator", sequenceName = "ingredient_id_seq", allocationSize = 1)
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

    @Column
    private LocalDateTime updated_at;

    @Column
    private LocalDateTime deleted_at;

    @Column
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(cascade = CascadeType.ALL)
    private List<IngredientHistory> ingredientsHistory;

    public Ingredient(String name, BigDecimal price, Integer quantity, String brand) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
    }

    public Ingredient(IngredientRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
        this.brand = request.getBrand();
    }

    @PrePersist
    private void PrePersist() {
        this.created_at = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updated_at = LocalDateTime.now();
    }

    public void markAsDeleted() {
        this.deleted = true;
        this.deleted_at = LocalDateTime.now();
    }

    public void updatePriceAndQuantity(BigDecimal newPrice, Integer newQuantity) {
        this.price = BigDecimal.valueOf(
                ((this.price.doubleValue() * this.quantity) + (newPrice.doubleValue() * newQuantity)) / (this.quantity
                        + newQuantity));

        this.quantity = this.quantity + newQuantity;
    }

}
