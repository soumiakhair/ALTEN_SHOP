package com.product.trial.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.trial.master.entities.Panier;


public interface PanierRepository extends JpaRepository<Panier, Long> {
}
