package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.repository.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

abstract class AbstractRepository<E, K> implements Repository<E, K> {

    @PersistenceContext
    EntityManager entityManager;

    final Class<E> entityClass;

    AbstractRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<E> getAll() {
        return entityManager
                .createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public Optional<E> findById(K id) {
        E entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public E add(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
    }
}
