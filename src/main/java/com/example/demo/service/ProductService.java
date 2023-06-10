package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.repo.ProductRepository;

@Component
public class ProductService implements IProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	Logger LOGGER =LoggerFactory.getLogger(ProductService.class);

	@Override
	public String createProduct(ProductEntity productEntity, Integer id, boolean isUpdate) throws Exception {
		if(productEntity == null)
			throw new Exception("request is null");
		//Update code
		if(isUpdate && id != null){
		ProductEntity oldProductEntity = productRepository.findById(id).get();
		   if(oldProductEntity == null) {
			   throw new NoSuchElementException("No Element found for this id");
		   }else {
			   productEntity=mapProductFields(oldProductEntity,productEntity);
		   }
			
		}
		if(isUpdate && id ==null) {
			throw new Exception("Id is null");
		}
		try {
			productRepository.save(productEntity);
			return"Sucess";
		}catch(Exception e)
		{
			LOGGER.error("Exception ocurred {} {} {}",e.getCause(),e.getMessage(),e.getStackTrace());
			return"Failed";
		}
		
	}

	private ProductEntity mapProductFields(ProductEntity oldProductEntity, ProductEntity productEntity) {
		productEntity.setProductId(oldProductEntity.getProductId());
		return productEntity;
	}

	@Override
	public ProductEntity getProductById(Integer id) throws Exception {
		if(id == null)
			throw new Exception("Id is null");
		
		Optional<ProductEntity> productEntity = productRepository.findById(id);
		if(productEntity.isPresent()) {
			return productEntity.get();
		}else {
			throw new NoSuchElementException("no Element found for this ID");
		}
			
	}

	@Override
	public String deleteProductById(Integer id) throws Exception {
		if(id == null)
			throw new Exception("Id is null");
		try {
			productRepository.deleteById(id);
			return "Sucess";
		}catch(Exception e) {
			LOGGER.error("Exception ocurred {} {} {}",e.getCause(),e.getMessage(),e.getStackTrace());
			return "Failed";
		}
		
	}

	@Override
	public List<ProductEntity> fetchProducts(Integer pageNumber, Integer size) {
		List<ProductEntity> responseEntities = new ArrayList<>();
		if(pageNumber == null) {
			responseEntities = productRepository.findAll();
		}else {
			size =size != null ? size :5;
			PageRequest pageable = PageRequest.of(pageNumber, size);
			Page<ProductEntity> productEntities;
			
			productEntities = productRepository.findAll(pageable);
			if(productEntities != null && productEntities.getContent() !=null ) {
				responseEntities = productEntities.getContent();
			}
		}
		return responseEntities;
	
	}

}
