package com.godev.budgetgo.service.factory;

/**
 * @param <T> from
 * @param <V> to
 */
public interface ConverterFactory<T, V> {
    V createFrom(T o);
}
