package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
