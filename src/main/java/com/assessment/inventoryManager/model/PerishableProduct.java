package com.assessment.inventoryManager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;


@Entity
@DiscriminatorValue("PERISHABLE")
public class PerishableProduct extends Product {
    private LocalDate expirationDate;

    public PerishableProduct(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public LocalDate expirationDate() {
        return this.expirationDate;
    }
}