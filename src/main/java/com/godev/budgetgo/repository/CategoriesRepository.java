package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Category;

import java.util.Optional;

public interface CategoriesRepository extends Repository<Category, Long> {
    Optional<Category> findByName(String name);
}
