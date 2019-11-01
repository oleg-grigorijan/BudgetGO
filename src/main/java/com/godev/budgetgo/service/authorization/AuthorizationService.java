package com.godev.budgetgo.service.authorization;

import java.util.List;

/**
 * @param <E> entity
 * @param <V> entity creation DTO
 * @param <U> entity patches DTO
 */
public interface AuthorizationService<E, V, U> {
    List<E> getAllAuthorizedEntities();

    void authorizeGet(E entity);

    void authorizeCreate(V creationDto);

    void authorizePatch(E entity, U patchesDto);
}
