package com.example.newsAPI.repositories;

import com.example.newsAPI.entiity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByCategoryTitle(String categoryTitle);
}
