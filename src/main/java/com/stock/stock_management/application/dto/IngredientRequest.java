package com.stock.stock_management.application.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequest {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero.")
    private BigDecimal price;

    @NotNull(message = "Quantity is required.")
    @PositiveOrZero(message = "Quantity must be greater than or equal to zero.")
    private Integer quantity;

    @NotBlank(message = "Brand is required.")
    private String brand;

}
