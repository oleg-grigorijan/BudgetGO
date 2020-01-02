package com.godev.budgetgo.data.impl;

import com.godev.budgetgo.data.CategoriesDataService;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.exception.CategoryNotFoundException;
import com.godev.budgetgo.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriesDataServiceImpl extends AbstractDataService<Category, Long> implements CategoriesDataService {

    private final CategoriesRepository repository;

    public CategoriesDataServiceImpl(CategoriesRepository repository) {
        super(repository, CategoryNotFoundException::byId);
        this.repository = repository;
    }

    @Override
    public Category getByName(String name) {
        return repository
                .findByName(name)
                .orElseThrow(() -> CategoryNotFoundException.byName(name));
    }
}
