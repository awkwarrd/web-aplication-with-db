package com.example.bd_project.services;

import com.example.bd_project.dto.CategoryDto;
import com.example.bd_project.model.Category;
import com.example.bd_project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CategoryDto categoryDto) {
        Category Category = new Category(categoryDto);
        return categoryRepository.save(Category);
    }


    public Category getCategoryById( Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDto.getName());
        category.setType(categoryDto.getType());
        return categoryRepository.save(category);
    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category deleted";
    }
}
