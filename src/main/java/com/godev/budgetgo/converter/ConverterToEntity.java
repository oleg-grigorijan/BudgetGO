package com.godev.budgetgo.converter;

/**
 * @param <T> creation dto
 * @param <E> entity
 */
public interface ConverterToEntity<T, E> {
    E convertToEntity(T dto);
}
