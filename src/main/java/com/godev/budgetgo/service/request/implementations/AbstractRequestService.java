package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.service.data.DataService;
import com.godev.budgetgo.service.factory.ConverterFactory;
import com.godev.budgetgo.service.merger.Merger;
import com.godev.budgetgo.service.request.RequestService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <E> entity
 * @param <K> entity key
 * @param <T> entity info DTO
 * @param <V> entity creation DTO
 * @param <U> entity patches DTO
 */
abstract class AbstractRequestService<E, K, T, V, U> implements RequestService<K, T, V, U> {

    private final DataService<E, K> dataService;
    private final ConverterFactory<V, E> entitiesFactory;
    private final ConverterFactory<E, T> dtoFactory;
    private final Merger<U, E> merger;

    public AbstractRequestService(
            DataService<E, K> dataService,
            ConverterFactory<V, E> entitiesFactory,
            ConverterFactory<E, T> dtoFactory,
            Merger<U, E> merger) {
        this.dataService = dataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
    }

    @Override
    public T getById(K id) {
        return dtoFactory.createFrom(dataService.getById(id));
    }

    @Override
    public List<T> getAll() {
        return dataService
                .getAll()
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public T create(V creationDto) {
        // TODO: Validation
        E entity = dataService.add(entitiesFactory.createFrom(creationDto));
        return dtoFactory.createFrom(entity);
    }

    @Transactional
    @Override
    public T patch(K id, U patchesDto) {
        E entity = dataService.getById(id);
        // TODO: Validation
        E patchedEntity = dataService.update(merger.merge(patchesDto, entity));
        return dtoFactory.createFrom(patchedEntity);
    }
}
