package com.godev.budgetgo.business.operation.impl;

import com.godev.budgetgo.business.base.BaseDataService;
import com.godev.budgetgo.business.operation.CategoriesDataService;
import com.godev.budgetgo.domain.operation.Category;
import com.godev.budgetgo.infra.error.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoriesDataServiceImpl extends BaseDataService<Category, Long> implements CategoriesDataService {

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
