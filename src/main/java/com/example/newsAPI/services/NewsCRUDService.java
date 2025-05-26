package com.example.newsAPI.services;

import com.example.newsAPI.dto.NewsDto;
import com.example.newsAPI.entiity.Category;
import com.example.newsAPI.entiity.News;
import com.example.newsAPI.exception.ResourceNotFoundException;
import com.example.newsAPI.repositories.CategoryRepository;
import com.example.newsAPI.repositories.NewsRepository;
import com.example.newsAPI.utils.NewsCategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsCRUDService implements CRUDService<NewsDto> {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final NewsCategoryMapper mapper;

    @Override
    public NewsDto getById(Long id) {
        log.info("Get by ID: " + id);
        return mapper.toDto(newsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("News with id " + id + " not found")
        ));
    }

    @Override
    public Collection<NewsDto> getAll() {
        log.info("Get all");
        Collection<NewsDto> dtos = newsRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        if (dtos.isEmpty()) {
            throw new ResourceNotFoundException("There are no news");
        } else {
            return dtos;
        }
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        log.info("Create");
        News news = mapper.toEntity(newsDto);
        Category category = categoryRepository.findByTitle(newsDto.getCategory()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found:" + newsDto.getCategory())
        );

        news.setCategory(category);
        newsRepository.save(news);
        return newsDto;
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        log.info("Update ");
        News news = mapper.toEntity(newsDto);
        Category category = categoryRepository.findByTitle(newsDto.getCategory()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found:" + newsDto.getCategory())
        );

        news.setCategory(category);
        newsRepository.save(news);
        return newsDto;
    }

    @Override
    public NewsDto deleteById(Long id) {
        log.info("Delete");
        NewsDto newsDto = mapper.toDto(newsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("News with id " + id + " not found")
        ));
        newsRepository.deleteById(id);
        return newsDto;
    }

    public Collection<NewsDto> getNewsByCategory(String categoryTitle) {
        Collection<NewsDto> dtos = newsRepository.findByCategoryTitle(categoryTitle)
                .stream()
                .map(mapper::toDto)
                .toList();
        if (dtos.isEmpty()) {
            throw new ResourceNotFoundException("There are no news with category " + categoryTitle);
        } else {
            return dtos;
        }
    }
}