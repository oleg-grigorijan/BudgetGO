package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.exception.NotFoundException;
import com.godev.budgetgo.repository.Repository;
import com.godev.budgetgo.service.data.DataService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

abstract class AbstractDataService<E, K> implements DataService<E, K> {

    private final Repository<E, K> repository;
    private final Function<K, ? extends NotFoundException> notFoundByIdExceptionBuilder;

    public AbstractDataService(
            Repository<E, K> repository,
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
        return repository.getAll();
    }

    @Transactional
    @Override
    public E add(E entity) {
        return repository.add(entity);
    }

    @Transactional
    @Override
    public E update(E entity) {
        return repository.update(entity);
    }

    @Transactional
    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }
}
