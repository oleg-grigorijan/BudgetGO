package com.godev.budgetgo.data.impl;

import com.godev.budgetgo.data.DataService;
import com.godev.budgetgo.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

abstract class AbstractDataService<E, K> implements DataService<E, K> {

    private final JpaRepository<E, K> repository;
    private final Function<K, ? extends NotFoundException> notFoundByIdExceptionBuilder;

    public AbstractDataService(
            JpaRepository<E, K> repository,
            Function<K, ? extends NotFoundException> notFoundByIdExceptionBuilder
    ) {
        this.repository = repository;
        this.notFoundByIdExceptionBuilder = notFoundByIdExceptionBuilder;
    }

    @Transactional(readOnly = true)
    @Override
    public E getById(K id) {
        return repository
                .findById(id)
                .orElseThrow(() -> notFoundByIdExceptionBuilder.apply(id));
    }


    @Transactional(readOnly = true)
    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public E add(E entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public E update(E entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }
}
