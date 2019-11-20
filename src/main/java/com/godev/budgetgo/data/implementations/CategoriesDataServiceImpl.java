package com.godev.budgetgo.data.implementations;

import com.godev.budgetgo.data.CategoriesDataService;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.exception.CategoryNotFoundException;
import com.godev.budgetgo.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

@Service
class CategoriesDataServiceImpl extends AbstractDataService<Category, Long> implements CategoriesDataService {

    public CategoriesDataServiceImpl(CategoriesRepository repository) {
        super(repository, CategoryNotFoundException::byId);
    }
}
