package com.example.demo.model;


import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "Category")
public class CategoryEntity {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "CategoryID")
	 private int categoryId;
	 @Column(name = "categoryName")
	 private String categoryName;
	 @Column(name = "IsActive")
	 private boolean isActive;
	 @Column(name = "CreatedOn")
	 private Date createdOn;
	 @Column(name = "UpdatedOn")
	 private Date updatedOn;
	 @Column(name = "CreatedBy")
	 private String createdBy;
	 @Column(name = "UpdatedBy")
	 private String updatedBy;
	 @OneToMany(mappedBy = "categoryEntity")
	 @JsonManagedReference
	 private List<ProductEntity> productEntityList ;
	 

	public CategoryEntity() {
		super();
	}


	public CategoryEntity(int categoryId, String categoryName, boolean isActive, Date createdOn, Date updatedOn,
			String createdBy, String updatedBy, List<ProductEntity> productEntityList) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.productEntityList = productEntityList;
	}

	@PrePersist
    protected void onCreate() {
        createdOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = new Date();
    }
    
	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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


	public List<ProductEntity> getProductEntityList() {
		return productEntityList;
	}


	public void setProductEntityList(List<ProductEntity> productEntityList) {
		this.productEntityList = productEntityList;
	}


	@Override
	public String toString() {
		return "CategoryEntity [categoryId=" + categoryId + ", categoryName=" + categoryName + ", isActive=" + isActive
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", productEntityList=" + productEntityList + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(categoryId, categoryName, createdBy, createdOn, isActive, productEntityList, updatedBy,
				updatedOn);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryEntity other = (CategoryEntity) obj;
		return categoryId == other.categoryId && Objects.equals(categoryName, other.categoryName)
				&& Objects.equals(createdBy, other.createdBy) && Objects.equals(createdOn, other.createdOn)
				&& isActive == other.isActive && Objects.equals(productEntityList, other.productEntityList)
				&& Objects.equals(updatedBy, other.updatedBy) && Objects.equals(updatedOn, other.updatedOn);
	}

    
	
	 

}
