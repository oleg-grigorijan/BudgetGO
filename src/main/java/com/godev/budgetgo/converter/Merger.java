package com.godev.budgetgo.converter;

/**
 * @param <E> entity
 * @param <T> patches dto
 */
public interface Merger<E, T> {
    E merge(E eOld, T dto);
}
