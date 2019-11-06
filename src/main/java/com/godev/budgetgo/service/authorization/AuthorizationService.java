package com.godev.budgetgo.service.authorization;

import java.util.List;

/**
 * @param <E> entity
 */
public interface AuthorizationService<E> {
    List<E> getAllAuthorizedEntities();

    void authorizeGet(E entity);

    void authorizeCreate(E entity);

    void authorizePatch(E entity, E patchedEntity);
}
