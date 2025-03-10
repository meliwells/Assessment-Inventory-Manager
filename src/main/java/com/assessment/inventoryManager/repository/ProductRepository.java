package com.assessment.inventoryManager.repository;

import com.assessment.inventoryManager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    //Optional<Product> findById(int productId);
    Optional<Product> findByProductName(String productName);
}
