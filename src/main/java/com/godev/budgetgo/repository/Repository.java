package com.godev.budgetgo.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E> {
    Optional<E> findById(Long id);

    List<E> findAll();

    E add(E entity);

    E update(E entity);

    void delete(E entity);
}