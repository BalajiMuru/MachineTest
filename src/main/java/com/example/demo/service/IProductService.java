package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ProductEntity;

public interface IProductService {

	String createProduct(ProductEntity productEntity,Integer id,boolean isUpdate)throws Exception;
	
	ProductEntity getProductById(Integer id)throws Exception;
	
	
	String deleteProductById(Integer id)throws Exception;
	
	List<ProductEntity> fetchProducts(Integer pageNumber, Integer size);
	
}
