package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.repository.CategoriesRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class CategoriesRepositoryImpl extends AbstractRepository<Category, Long> implements CategoriesRepository {

    CategoriesRepositoryImpl() {
        super(Category.class);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return entityManager
                .createQuery("SELECT c FROM Category c WHERE c.name = :name", entityClass)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }
}
