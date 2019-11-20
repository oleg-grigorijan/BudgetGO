package com.godev.budgetgo.data;

import java.util.List;

/**
 * @param <E> entity
 * @param <K> entity id
 */
public interface DataService<E, K> {
    E getById(K id);

    List<E> getAll();

    E add(E entity);

    E update(E entity);

    void delete(E entity);
}
