package com.example.newsAPI.controllers;

import com.example.newsAPI.dto.NewsDto;
import com.example.newsAPI.services.NewsCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsCRUDService newsCRUDService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDto getNewsById(@PathVariable("id") Long id) {
        return newsCRUDService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<NewsDto> getAllNews() {
        return newsCRUDService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDto addNews(@RequestBody NewsDto newsDto) {
        return newsCRUDService.create(newsDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public NewsDto updateNews(@RequestBody NewsDto newsDto) {
        return newsCRUDService.update(newsDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDto deleteNews(@PathVariable("id") Long id) {
        return newsCRUDService.deleteById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Collection<NewsDto> getNewsByCategory(@RequestParam String categoryTitle) {
        return newsCRUDService.getNewsByCategory(categoryTitle);
    }
}
