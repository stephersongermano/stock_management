package com.stock.stock_management.shared.exception;

import jakarta.persistence.EntityNotFoundException;

public class IdNotFoundException extends EntityNotFoundException {

    public IdNotFoundException(Long id) {
        super("Produto com id " + id + " não encontrado");
    }

}
