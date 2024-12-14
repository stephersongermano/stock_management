package com.stock.stock_management.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stock_entry")
public class StockEntry {

    @Id
    @GeneratedValue(generator = "ingredient_id_seq_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ingredient_id_seq_generator", sequenceName = "ingredient_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate = LocalDateTime.now();

    @Column(name = "note_number", nullable = false)
    private Integer noteNumber;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "stock_entry_ingredients", joinColumns = @JoinColumn(name = "stock_entry_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public StockEntry(Integer noteNumber, BigDecimal totalValue, List<Ingredient> ingredients) {
        this.noteNumber = noteNumber;
        this.totalValue = totalValue;
        this.ingredients = ingredients;
    }

}
