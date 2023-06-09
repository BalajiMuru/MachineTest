package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CategoryEntity;
import com.example.demo.repo.CategaryRepository;
import com.example.demo.service.CategoryServiceI;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
    private CategaryRepository categoryRepository;
	
	@Autowired
	private CategoryServiceI categoryServiceI;

    @GetMapping
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public CategoryEntity createCategory(@RequestBody CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/{id}")
    public CategoryEntity getCategoryById(@PathVariable("id") int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    @PutMapping("/{id}")
    public CategoryEntity updateCategory(@PathVariable("id") int id, @RequestBody CategoryEntity category) {
        CategoryEntity existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found"));

        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") int id) {
        categoryRepository.deleteById(id);
    }
}
