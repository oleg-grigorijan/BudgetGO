package com.godev.budgetgo.service.request;

import java.util.List;

/**
 * @param <K> entity id
 * @param <T> entity info DTO
 * @param <V> entity creation DTO
 * @param <U> entity patches DTO
 */
public interface RequestService<K, T, V, U> {
    T getById(K id);

    List<T> getAll();

    T create(V creationDto);

    T patch(K id, U patchesDto);
}
