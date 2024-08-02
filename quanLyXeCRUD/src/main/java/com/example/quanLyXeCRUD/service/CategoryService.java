package com.example.quanLyXeCRUD.service;

import com.example.quanLyXeCRUD.entity.Category;
import com.example.quanLyXeCRUD.exception.ResourceNotFoundException;
import com.example.quanLyXeCRUD.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        // Validate name
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category name already exists.");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(int id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // Validate name
        if (!existingCategory.getName().equals(category.getName()) &&
                categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category name already exists.");
        }

        existingCategory.setName(category.getName());
        existingCategory.setUpdate_user(category.getUpdate_user());
        existingCategory.set_deleted(category.is_deleted());

        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // Validate if category has accessories
        if (!category.getAccessories().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete category with accessories.");
        }

        categoryRepository.delete(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
}

