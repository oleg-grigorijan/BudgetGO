package com.godev.budgetgo.business.base;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <E> entity
 * @param <T> info dto
 */
public interface ConverterToDto<E, T> {

    T convertToDto(E e);

    default List<T> convertToDtos(Collection<E> entities) {
        return entities
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
