package com.example.newsAPI.utils;

import com.example.newsAPI.dto.CategoryDto;
import com.example.newsAPI.dto.NewsDto;
import com.example.newsAPI.entiity.Category;
import com.example.newsAPI.entiity.News;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(componentModel = "spring")
public interface NewsCategoryMapper {

    NewsCategoryMapper INSTANCE = Mappers.getMapper(NewsCategoryMapper.class);

    @Mapping(target = "category", ignore = true)
    News toEntity(NewsDto newsDto);

    @Mapping(target = "category", source = "category.title")
    NewsDto toDto(News news);

    CategoryDto toDto(Category category);

    @Mapping(target = "news", ignore = true)
    Category toEntity(CategoryDto categoryDto);
}
