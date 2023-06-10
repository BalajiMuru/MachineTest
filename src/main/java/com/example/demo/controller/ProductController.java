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

import com.example.demo.model.ProductEntity;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.IProductService;

@RestController
@RequestMapping("api/product")
public class ProductController {

	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
	private IProductService iProductService;

	
//    @GetMapping
//    public List<ProductEntity> getAllProducts() {
//        return productRepository.findAll();
//    }
    
    @GetMapping("/")
    public ResponseEntity getAllProducts(@RequestParam(name = "pageNumber") Integer pageNumber, @RequestParam(name = "size") Integer size) {
		return new ResponseEntity<>(iProductService.fetchProducts(pageNumber, size),HttpStatus.OK);}
    
    
    @PostMapping("/")
    public ResponseEntity createProduct(@RequestBody ProductEntity product) throws Exception {
        return new ResponseEntity<>(this.iProductService.createProduct(product, null, false),HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(this.iProductService.getProductById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") int id, @RequestBody ProductEntity product) throws Exception {
        return new ResponseEntity<>(this.iProductService.createProduct(product, id, true),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id) {
        productRepository.deleteById(id);
    }
}
