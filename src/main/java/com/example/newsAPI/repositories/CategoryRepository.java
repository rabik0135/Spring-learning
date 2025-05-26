package com.example.newsAPI.repositories;

import com.example.newsAPI.entiity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
    boolean existsByTitle(String title);
}
