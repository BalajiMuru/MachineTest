package com.example.demo.repo;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CategoryEntity;

public interface CategaryRepository extends JpaRepository<CategoryEntity, Integer> {

}
