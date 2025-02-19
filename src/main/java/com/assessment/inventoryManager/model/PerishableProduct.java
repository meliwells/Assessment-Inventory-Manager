package com.assessment.inventoryManager.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;


public interface PerishableProduct {
    LocalDate expirationDate();
}
