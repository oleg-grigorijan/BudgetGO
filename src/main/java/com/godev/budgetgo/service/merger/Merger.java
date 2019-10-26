package com.godev.budgetgo.service.merger;

/**
 * @param <S> source
 * @param <V> destination
 */
public interface Merger<S, V> {
    V merge(S what, V into);
}
