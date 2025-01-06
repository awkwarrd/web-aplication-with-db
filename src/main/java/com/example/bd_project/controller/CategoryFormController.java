package com.example.bd_project.controller;

import com.example.bd_project.dto.CategoryDto;
import com.example.bd_project.dto.CategoryDto;
import com.example.bd_project.dto.CategoryDto;
import com.example.bd_project.model.Category;
import com.example.bd_project.model.Category;
import com.example.bd_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryFormController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String showCategoryPage(Model model) {
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("categoryDto", new CategoryDto());
        return "category-table-and-form";
    }

    @PostMapping()
    public String addCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return "redirect:/category"; // Перезагрузка страницы после добавления
    }

    @PostMapping("{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setType(category.getType());
        model.addAttribute("categoryDto", categoryDto);
        model.addAttribute("categoryId", id);
        return "edit-category-form";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.updateCategory(id, categoryDto);
        return "redirect:/category";
    }
}
