package com.be.controller;

import com.be.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/{entity}")
public class GenericController<T, ID> {

    private final GenericService<T, ID> service;

    @Autowired
    public GenericController(GenericService<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> getAll(@PathVariable("entity") String entityName) throws ClassNotFoundException {
        Class<T> entityType = (Class<T>) Class.forName("com.be.entity." + entityName);
        return service.findAll(entityType);
    }

    @GetMapping("/{id}")
    public Optional<T> getById(@PathVariable("entity") String entityName, @PathVariable ID id) throws ClassNotFoundException {
        Class<T> entityType = (Class<T>) Class.forName("com.be.entity." + entityName);
        return service.findById(entityType, id);
    }

    @PostMapping
    public T create(@PathVariable("entity") String entityName, @RequestBody T entity) throws ClassNotFoundException {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable("entity") String entityName, @PathVariable ID id, @RequestBody T entity) throws ClassNotFoundException {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("entity") String entityName, @PathVariable ID id) throws ClassNotFoundException {
        Class<T> entityType = (Class<T>) Class.forName("com.be.entity." + entityName);
        service.deleteById(entityType, id);
    }
}

