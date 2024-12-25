package com.stock.stock_management.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.stock.stock_management.dto.IngredientRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
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

    @Column(nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column
    private LocalDateTime updated_at;

    @Column
    private LocalDateTime deleted_at;

    @Column
    private boolean deleted = Boolean.FALSE;

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

    public BigDecimal priceUpdate(BigDecimal newPrice, Integer newQuantity) {
        setUpdatedAt();
        return this.price = BigDecimal.valueOf(
                ((this.price.doubleValue() * this.quantity) + (newPrice.doubleValue() * newQuantity)) / (this.quantity
                        + newQuantity));
    }

    public Integer quantityUpdate(Integer newQuantity) {
        return this.quantity = this.quantity + newQuantity;
    }

    public void updatePriceAndQuantity(BigDecimal newPrice, Integer newQuantity) {
        priceUpdate(newPrice, newQuantity);

        quantityUpdate(newQuantity);
    }

    public void setUpdatedAt() {
        this.updated_at = LocalDateTime.now();
    }

    public void setDeletedAt() {
        this.deleted_at = LocalDateTime.now();
    }

}
