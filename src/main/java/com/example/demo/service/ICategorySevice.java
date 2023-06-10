package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CategoryEntity;

public interface ICategorySevice 
{

	String save(CategoryEntity categoryEntity, Integer id, boolean isUpadte) throws Exception;
	
	CategoryEntity getCategoryById(Integer id)throws Exception;
	
	String deleteCategoryById(Integer id)throws Exception;
	
	List<CategoryEntity> fetchCategories(Integer pageNumber, Integer size);
}
