package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<E> implements Repository<E> {
    protected EntityManager entityManager;
    protected final Class<E> entityClass;

    AbstractRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<E> findAll() {
        return entityManager
                .createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public Optional<E> findById(Long id) {
        E entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public E add(E entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public E update(E entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(E entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
