package com.godev.budgetgo.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <E> entity
 * @param <T> info dto
 */
public interface EntityConverter<E, T> {
    T convertFromEntity(E e);

    default List<T> convertFromEntities(Collection<E> entities) {
        return entities
                .stream()
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }
}
