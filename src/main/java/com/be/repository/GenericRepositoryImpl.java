package com.be.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenericRepositoryImpl<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<T> findAll(Class<T> entityType) {
        return entityManager.createQuery("FROM " + entityType.getSimpleName(), entityType).getResultList();
    }

    public Optional<T> findById(Class<T> entityType, ID id) {
        return Optional.ofNullable(entityManager.find(entityType, id));
    }

    @Transactional
    public T save(T entity) {
        if (entityManager.contains(entity)) {
            return entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
            return entity;
        }
    }

    @Transactional
    public void deleteById(Class<T> entityType, ID id) {
        T entity = entityManager.find(entityType, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
