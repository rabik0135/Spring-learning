package com.example.newsAPI.services;

import java.util.Collection;

public interface CRUDService<T> {
    T getById(Long id);
    Collection<T> getAll();
    T create(T item);
    void update(Long id, T item);
    T deleteById(Long id);
}
