package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.example.demo.model.CategoryEntity;
import com.example.demo.repo.CategaryRepository;

@Component
public class CategoryService implements ICategorySevice {
	
  @Autowired	
  CategaryRepository categaryRepository;
	
	Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
	

	@Override
	public String save(CategoryEntity categoryEntity, Integer id, boolean isUpdate) throws Exception {
		if(categoryEntity == null)
			throw new Exception("request is null");
		
		if(isUpdate && id != null) {
			CategoryEntity oldCategory = categaryRepository.findById(id).get();
			if(oldCategory == null) {
				throw new NoSuchElementException("No Element found for this ID");
			}else {
				categoryEntity=mapCategoryFields(oldCategory,categoryEntity);
			}
		}
		
		if(isUpdate && id == null)
		{
			throw new Exception("id is null");
		}
		
		try {
			categaryRepository.save(categoryEntity);
			return "sucess";
		}catch (Exception e) {
			LOGGER.error("Exception occured {} {} {}",e.getCause(),e.getMessage(),e.getStackTrace() );
			return "Failed";
		}
		
	}

	private CategoryEntity mapCategoryFields(CategoryEntity oldCategory, CategoryEntity categoryEntity) {
		categoryEntity.setCategoryId(oldCategory.getCategoryId());
		return categoryEntity;
	}

	@Override
	public CategoryEntity getCategoryById(Integer id) throws Exception {
	 if(id == null)
		 throw new Exception("Id is null");
	 Optional<CategoryEntity> category = categaryRepository.findById(id);
	 if(category.isPresent())
	 {
		 return category.get();
	 }else {
		 throw new NoSuchElementException("No Element found for this ID");
	 }
		 
	}

	@Override
	public String deleteCategoryById(Integer id) throws Exception {
		if(id == null)
			throw new Exception("Id is null");
		try {
			categaryRepository.deleteById(id);
			return "Sucess";
		}catch(Exception e) {
			LOGGER.error("Exception ocurred {} {} {}",e.getCause(),e.getMessage(),e.getStackTrace());
			return "Failed";
		}
	}

	@Override
	public List<CategoryEntity> fetchCategories(Integer pageNumber, Integer size) {
		
		List<CategoryEntity> responseEntities = new ArrayList<>();
		if(pageNumber == null) {
			responseEntities = categaryRepository.findAll();
		}else {
			size =size != null ? size :5;
			PageRequest pageable = PageRequest.of(pageNumber, size);
			Page<CategoryEntity> categoryEntities;
			
			categoryEntities = categaryRepository.findAll(pageable);
			if(categoryEntities != null && categoryEntities.getContent() !=null ) {
				responseEntities = categoryEntities.getContent();
			}
		}
		return responseEntities;
	}

}
