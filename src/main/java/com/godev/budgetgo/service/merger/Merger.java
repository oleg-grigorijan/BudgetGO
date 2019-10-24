package com.godev.budgetgo.service.merger;

/**
 * @param <S> source
 * @param <V> destination
 */
public interface Merger<S, V> {
    void merge(S what, V into);
}
