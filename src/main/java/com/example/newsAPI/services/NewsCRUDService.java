package com.example.newsAPI.services;

import com.example.newsAPI.dto.NewsDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NewsCRUDService implements CRUDService<NewsDto> {

    private final ConcurrentHashMap<Long, NewsDto> storage = new ConcurrentHashMap<>();

    @Override
    public NewsDto getById(Long id) {
        System.out.println("Get by ID: " + id);
        return storage.get(id);
    }

    @Override
    public Collection<NewsDto> getAll() {
        System.out.println("Get all");
        return storage.values();
    }

    @Override
    public NewsDto create(NewsDto item) {
        System.out.println("Create");
        Long nextId = (storage.isEmpty() ? 0 : (storage.mappingCount())) + 1;
        item.setId(nextId);
        item.setDate(Instant.now());
        storage.put(nextId, item);
        return item;
    }

    @Override
    public void update(Long id, NewsDto item) {
        System.out.println("Update " + id);
        if (!storage.containsKey(id)) {
            return;
        }
        item.setId(id);
        item.setDate(Instant.now());
        storage.put(id, item);
    }

    @Override
    public NewsDto deleteById(Long id) {
        System.out.println("Delete");
        if (!storage.containsKey(id)) {
            return null;
        }
        return storage.remove(id);
    }
}