package com.product.trial.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.trial.master.entities.Panier;
import com.product.trial.master.repository.PanierRepository;

@Service
public class PanierService {
    
    @Autowired
    private PanierRepository panierRepository;

    public List<Panier> getAllPanier() {
        return panierRepository.findAll();
    }

    public Optional<Panier> getPanierById(Long id) {
        return panierRepository.findById(id);
    }

    public Panier createPanier(Panier Panier) {
        return panierRepository.save(Panier);
    }

    public Optional<Panier> updatePanier(Long id, Panier PanierDetails) {
        return panierRepository.findById(id)
            .map(existingPanier -> {
            	existingPanier.setCode(PanierDetails.getCode());
            	existingPanier.setName(PanierDetails.getName());
            	existingPanier.setDescription(PanierDetails.getDescription());
            	existingPanier.setImage(PanierDetails.getImage());
            	existingPanier.setCategory(PanierDetails.getCategory());
            	existingPanier.setPrice(PanierDetails.getPrice());
            	existingPanier.setQuantity(PanierDetails.getQuantity());
            	existingPanier.setInternalReference(PanierDetails.getInternalReference());
            	existingPanier.setShellId(PanierDetails.getShellId());
            	existingPanier.setInventoryStatus(PanierDetails.getInventoryStatus());
            	existingPanier.setRating(PanierDetails.getRating());
                return panierRepository.save(existingPanier);
            });
    }

    public void deletePanier(Long id) {
    	panierRepository.deleteById(id);
    }
}
