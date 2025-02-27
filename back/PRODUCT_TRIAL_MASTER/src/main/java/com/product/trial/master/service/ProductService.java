package com.product.trial.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.trial.master.entities.Product;
import com.product.trial.master.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
            .map(existingProduct -> {
                existingProduct.setCode(productDetails.getCode());
                existingProduct.setName(productDetails.getName());
                existingProduct.setDescription(productDetails.getDescription());
                existingProduct.setImage(productDetails.getImage());
                existingProduct.setCategory(productDetails.getCategory());
                existingProduct.setPrice(productDetails.getPrice());
                existingProduct.setQuantity(productDetails.getQuantity());
                existingProduct.setInternalReference(productDetails.getInternalReference());
                existingProduct.setShellId(productDetails.getShellId());
                existingProduct.setInventoryStatus(productDetails.getInventoryStatus());
                existingProduct.setRating(productDetails.getRating());
                return productRepository.save(existingProduct);
            });
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
