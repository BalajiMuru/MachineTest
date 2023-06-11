package com.example.demo.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
    private int categoryId;
	@Column(name = "Name")
    private String  name; 
	@Column(name = "Description")
	private String description; 
	@Column(name = "Price")
	private int price;
	@Column(name = "ImagePath")
	private String imagePath ;
	@Column(name = "Soldout")
	private boolean soldout ;
	@Column(name = "Attribute1")
	private String attribute1 ;
	@Column(name = "Attribute2")
	private String attribute2 ;
	@Column(name = "IsActive")
	private boolean isActive ;
	@Column(name = "CreatedOn")
	private Date createdOn ;
	@Column(name = "UpdatedOn")
	private Date updatedOn ;
	@Column(name = "CreatedBy")
	private String createdBy ;
	@Column(name = "UpdatedBy")
	private String updatedBy ;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "CategoryFK")
	private CategoryEntity categoryEntity;

	public ProductEntity() {
		super();
	}

	public ProductEntity(int productId, int categoryId, String name, String description, int price, String imagePath,
			boolean soldout, String attribute1, String attribute2, boolean isActive, Date createdOn, Date updatedOn,
			String createdBy, String updatedBy, CategoryEntity categoryEntity) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imagePath = imagePath;
		this.soldout = soldout;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.categoryEntity = categoryEntity;
	}

	@PrePersist
    protected void onCreate() {
        createdOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = new Date();
    }
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isSoldout() {
		return soldout;
	}

	public void setSoldout(boolean soldout) {
		this.soldout = soldout;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	@Override
	public String toString() {
		return "ProductEntity [productId=" + productId + ", categoryId=" + categoryId + ", name=" + name
				+ ", description=" + description + ", price=" + price + ", imagePath=" + imagePath + ", soldout="
				+ soldout + ", attribute1=" + attribute1 + ", attribute2=" + attribute2 + ", isActive=" + isActive
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", categoryEntity=" + categoryEntity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attribute1, attribute2, categoryEntity, categoryId, createdBy, createdOn, description,
				imagePath, isActive, name, price, productId, soldout, updatedBy, updatedOn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntity other = (ProductEntity) obj;
		return Objects.equals(attribute1, other.attribute1) && Objects.equals(attribute2, other.attribute2)
				&& Objects.equals(categoryEntity, other.categoryEntity) && categoryId == other.categoryId
				&& Objects.equals(createdBy, other.createdBy) && Objects.equals(createdOn, other.createdOn)
				&& Objects.equals(description, other.description) && Objects.equals(imagePath, other.imagePath)
				&& isActive == other.isActive && Objects.equals(name, other.name) && price == other.price
				&& productId == other.productId && soldout == other.soldout
				&& Objects.equals(updatedBy, other.updatedBy) && Objects.equals(updatedOn, other.updatedOn);
	}

	
	
	
}
