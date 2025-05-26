package com.example.newsAPI.services;

import com.example.newsAPI.dto.CategoryDto;
import com.example.newsAPI.exception.ResourceNotFoundException;
import com.example.newsAPI.repositories.CategoryRepository;
import com.example.newsAPI.utils.NewsCategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryCRUDService implements CRUDService<CategoryDto> {

    private final CategoryRepository categoryRepository;
    private final NewsCategoryMapper mapper;

    @Override
    public CategoryDto getById(Long id) {
        log.info("Get by ID: " + id);
        return mapper.toDto(categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category with id " + id + " not found")
        ));
    }

    @Override
    public Collection<CategoryDto> getAll() {
        log.info("Get all");
        Collection<CategoryDto> dtos = categoryRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        if (dtos.isEmpty()) {
            throw new ResourceNotFoundException("There are no categories");
        } else {
            return dtos;
        }
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        log.info("Create");
        if (categoryRepository.existsByTitle(categoryDto.getTitle())) {
            throw new ResourceNotFoundException("Category " + categoryDto.getTitle() + " is already exists!");
        }
        categoryRepository.save(mapper.toEntity(categoryDto));
        return categoryDto;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        log.info("Update");
        if (categoryRepository.existsByTitle(categoryDto.getTitle())) {
            throw new ResourceNotFoundException("Category " + categoryDto.getTitle() + " is already exists!");
        }
        categoryRepository.save(mapper.toEntity(categoryDto));
        return categoryDto;
    }

    @Override
    public CategoryDto deleteById(Long id) {
        log.info("Delete");
        CategoryDto categoryDto = mapper.toDto(categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category with id " + id + " not found")
        ));
        categoryRepository.deleteById(id);
        return categoryDto;
    }
}
