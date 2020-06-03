package com.godev.budgetgo.business.operation.impl;

import com.godev.budgetgo.domain.operation.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
