package com.product.trial.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.trial.master.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
