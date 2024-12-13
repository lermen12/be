package com.be.service;

import com.be.repository.GenericRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenericService<T, ID> {

    private final GenericRepositoryImpl<T, ID> repository;

    @Autowired
    public GenericService(GenericRepositoryImpl<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> findAll(Class<T> entityType) {
        return repository.findAll(entityType);
    }

    public Optional<T> findById(Class<T> entityType, ID id) {
        return repository.findById(entityType, id);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void deleteById(Class<T> entityType, ID id) {
        repository.deleteById(entityType, id);
    }
}
