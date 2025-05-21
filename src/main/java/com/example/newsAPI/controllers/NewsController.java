package com.example.newsAPI.controllers;

import com.example.newsAPI.dto.ErrorResponseDto;
import com.example.newsAPI.dto.NewsDto;
import com.example.newsAPI.services.NewsCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(("/news"))
@RequiredArgsConstructor
public class NewsController {
    private final NewsCRUDService service;

    @GetMapping("/{id}")
    public ResponseEntity getNewsById(@PathVariable("id") Long id) {
        NewsDto news = service.getById(id);
        if (news == null) {
            ErrorResponseDto error = new ErrorResponseDto("Новость с ID " + id + " не найдена");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity getAllNews() {
        if (service.getAll().isEmpty()) {
            ErrorResponseDto error = new ErrorResponseDto("Новостей нет!");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }

        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity addNews(@RequestBody NewsDto news) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(news));
    }

    @PutMapping()
    public ResponseEntity updateNews(@RequestBody NewsDto news) {
        Long id = news.getId();
        service.update(id, news);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(news);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNews(@PathVariable("id") Long id) {
        if (service.deleteById(id) == null) {
            ErrorResponseDto error = new ErrorResponseDto("Новость с ID " + id + " не найдена");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
