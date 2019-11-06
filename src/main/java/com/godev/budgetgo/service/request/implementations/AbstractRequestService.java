package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.service.authorization.AuthorizationService;
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
    private final AuthorizationService<E> authorizationService;

    public AbstractRequestService(
            DataService<E, K> dataService,
            ConverterFactory<V, E> entitiesFactory,
            ConverterFactory<E, T> dtoFactory,
            Merger<U, E> merger,
            AuthorizationService<E> authorizationService
    ) {
        this.dataService = dataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
        this.authorizationService = authorizationService;
    }

    @Override
    public T getById(K id) {
        E entity = dataService.getById(id);
        authorizationService.authorizeGet(entity);
        return dtoFactory.createFrom(entity);
    }

    @Override
    public List<T> getAll() {
        return authorizationService
                .getAllAuthorizedEntities()
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public T create(V creationDto) {
        E entity = entitiesFactory.createFrom(creationDto);
        authorizationService.authorizeCreate(entity);
        // TODO: Validation
        E savedEntity = dataService.add(entity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public T patch(K id, U patchesDto) {
        E entity = dataService.getById(id);
        E patchedEntity = merger.merge(patchesDto, entity);
        authorizationService.authorizePatch(entity, patchedEntity);
        // TODO: Validation
        E savedEntity = dataService.update(patchedEntity);
        return dtoFactory.createFrom(savedEntity);
    }
}
