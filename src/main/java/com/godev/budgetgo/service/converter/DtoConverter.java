package com.godev.budgetgo.service.converter;

/**
 * @param <T> creation dto
 * @param <E> entity
 */
public interface DtoConverter<T, E> {
    E convertFromDto(T dto);
}
