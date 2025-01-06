package com.example.bd_project.repository;
import com.example.bd_project.model.Category;
import com.example.bd_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}

