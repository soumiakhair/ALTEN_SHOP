package com.product.trial.master.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.trial.master.entities.Panier;
import com.product.trial.master.service.PanierService;

@RestController
@RequestMapping("/api/paniers")
@CrossOrigin(origins = "*")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @GetMapping
    public List<Panier> getAllPanier() {
        return panierService.getAllPanier();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Panier> getPanierById(@PathVariable Long id) {
        return panierService.getPanierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Panier createPanier(@RequestBody Panier product) {
        return panierService.createPanier(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Panier> updatePanier(@PathVariable Long id, @RequestBody Panier productDetails) {
        return panierService.updatePanier(id, productDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePanier(@PathVariable Long id) {
    	panierService.deletePanier(id);
        return ResponseEntity.ok().build();
    }
}

