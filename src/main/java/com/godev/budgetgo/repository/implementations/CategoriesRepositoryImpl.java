package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.repository.CategoriesRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriesRepositoryImpl extends AbstractRepository<Category, Long> implements CategoriesRepository {
    CategoriesRepositoryImpl() {
        super(Category.class);
    }
}
