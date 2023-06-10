package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CategoryEntity;
import com.example.demo.repo.CategaryRepository;
import com.example.demo.service.ICategorySevice;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
    private CategaryRepository categoryRepository;
	
	@Autowired
	private ICategorySevice iCategoryService;

//    @GetMapping
//    public List<CategoryEntity> getAllCategories() {
//        return categoryRepository.findAll();
//    }
    
    @GetMapping("/")
    public ResponseEntity getAllCategories(@RequestParam(name = "pageNumber") Integer pageNumber, @RequestParam(name = "size") Integer size) {
		return new ResponseEntity<>(iCategoryService.fetchCategories(pageNumber, size),HttpStatus.OK);
    	
    }
    

    @PostMapping("/")
    public ResponseEntity createCategory(@RequestBody CategoryEntity category) throws Exception {
        return new ResponseEntity<>(this.iCategoryService.save(category, null, false),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CategoryEntity getCategoryById(@PathVariable("id") int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryEntity category) {
        try {
        	this.iCategoryService.save(category, id, true);
        	return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
        	return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);		
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") int id) {
        categoryRepository.deleteById(id);
    }
}
