package com.stock.stock_management.domain.entity;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "stock_entry")
public class StockEntry {

    @Id
    @GeneratedValue(generator = "stock_entry_id_seq_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "stock_entry_id_seq_generator", sequenceName = "stock_entry_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "entry_date", nullable = false, updatable = false)
    private LocalDateTime entryDate;

    @Column(name = "note_number", nullable = false)
    private Integer noteNumber;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "stock_entry_ingredients", joinColumns = @JoinColumn(name = "stock_entry_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_history_id"))
    private List<IngredientHistory> ingredientsHistory;

    public StockEntry(Integer noteNumber, BigDecimal totalValue, List<IngredientHistory> ingredientsHistory) {
        this.noteNumber = noteNumber;
        this.totalValue = totalValue;
        this.ingredientsHistory = ingredientsHistory;
    }

    @PrePersist
    private void setEntryDate() {
        this.entryDate = LocalDateTime.now();
    }

}
