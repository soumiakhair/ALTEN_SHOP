package com.product.trial.master.entities;

import java.time.Instant;

import com.product.trial.master.entities.Product.InventoryStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "panier")
public class Panier {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String code;
	    private String name;
	    private String description;
	    private String image;
	    private String category;
	    private Double price;
	    private Integer quantity;
	    private String internalReference;
	    private Long shellId;
	    @Enumerated(EnumType.STRING)
	    private InventoryStatus inventoryStatus;
	    private Integer rating;
	    private Instant createdAt;
	    private Instant updatedAt;

	    public enum InventoryStatus {
	        INSTOCK, LOWSTOCK, OUTOFSTOCK
	    }

	    

	    @PrePersist
	    protected void onCreate() {
	        createdAt = Instant.now();
	        updatedAt = Instant.now();
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        updatedAt = Instant.now();
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getInternalReference() {
			return internalReference;
		}

		public void setInternalReference(String internalReference) {
			this.internalReference = internalReference;
		}

		public Long getShellId() {
			return shellId;
		}

		public void setShellId(Long shellId) {
			this.shellId = shellId;
		}

		public InventoryStatus getInventoryStatus() {
			return inventoryStatus;
		}

		public void setInventoryStatus(InventoryStatus inventoryStatus) {
			this.inventoryStatus = inventoryStatus;
		}

		public Integer getRating() {
			return rating;
		}

		public void setRating(Integer rating) {
			this.rating = rating;
		}

		public Instant getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Instant createdAt) {
			this.createdAt = createdAt;
		}

		public Instant getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Instant updatedAt) {
			this.updatedAt = updatedAt;
		}

}
