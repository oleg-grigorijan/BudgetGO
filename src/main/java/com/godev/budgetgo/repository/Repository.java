package com.godev.budgetgo.repository;

import java.util.List;
import java.util.Optional;

/**
 * @param <E> entity
 * @param <K> entity id
 */
public interface Repository<E, K> {
    Optional<E> findById(K id);

    List<E> findAll();

    E add(E entity);

    E update(E entity);

    void delete(E entity);
}