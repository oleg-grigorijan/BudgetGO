package com.godev.budgetgo.business.base;

/**
 * @param <E> entity
 * @param <T> patches dto
 */
public interface Merger<E, T> {

    E merge(E eOld, T dto);
}
