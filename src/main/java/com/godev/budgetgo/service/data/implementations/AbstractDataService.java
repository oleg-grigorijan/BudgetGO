package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.exception.NotFoundExcepion;
import com.godev.budgetgo.repository.Repository;
import com.godev.budgetgo.service.data.DataService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

abstract class AbstractDataService<E, K> implements DataService<E, K> {

    private final Repository<E, K> repository;
    protected final Supplier<? extends NotFoundExcepion> notFoundExceptionSupplier;

    public AbstractDataService(
            Repository<E, K> repository,
            Supplier<? extends NotFoundExcepion> notFoundExceptionSupplier
    ) {
        this.repository = repository;
        this.notFoundExceptionSupplier = notFoundExceptionSupplier;
    }

    @Override
    public E getById(K id) {
        return repository
                .findById(id)
                .orElseThrow(notFoundExceptionSupplier);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
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
