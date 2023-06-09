package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.CategoryEntity;
import com.example.demo.repo.CategaryRepository;

@Component
public class CategoryService implements CategoryServiceI{
	
  @Autowired	
  CategaryRepository categaryRepository;
	
	@Override
	public void save(CategoryEntity categoryEntity) throws Exception {
		if(categoryEntity == null)
			throw new Exception("request is null");
		
		categaryRepository.save(categoryEntity);
		
	}

}
