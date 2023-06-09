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

import com.example.demo.model.ProductModel;
import com.example.demo.repo.ProductRepository;

public class ProductController {

	@Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }
    @PostMapping
    public ProductModel createProduct(@RequestBody ProductModel product) {
        return productRepository.save(product);
    }
    
    @GetMapping("/{id}")
    public ProductModel getProductById(@PathVariable("id") int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @PutMapping("/{id}")
    public ProductModel updateProduct(@PathVariable("id") int id, @RequestBody ProductModel product) {
        ProductModel existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        existingProduct.setName(product.getName());
        return productRepository.save(existingProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id) {
        productRepository.deleteById(id);
    }
}
