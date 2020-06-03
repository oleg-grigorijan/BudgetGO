package com.godev.budgetgo.business.base;

import com.godev.budgetgo.infra.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class BaseDataService<E, K> implements DataService<E, K> {

    private final JpaRepository<E, K> repository;

    private final Function<K, ? extends NotFoundException> notFoundByIdExceptionBuilder;

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
