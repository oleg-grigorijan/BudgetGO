package com.godev.budgetgo.business.base;

/**
 * @param <T> creation dto
 * @param <E> entity
 */
public interface ConverterToEntity<T, E> {

    E convertToEntity(T dto);
}
