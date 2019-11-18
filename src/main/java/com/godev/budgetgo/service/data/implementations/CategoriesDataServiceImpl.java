package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.exception.CategoryNotFoundException;
import com.godev.budgetgo.repository.CategoriesRepository;
import com.godev.budgetgo.service.data.CategoriesDataService;
import org.springframework.stereotype.Service;

@Service
class CategoriesDataServiceImpl extends AbstractDataService<Category, Long> implements CategoriesDataService {

    public CategoriesDataServiceImpl(CategoriesRepository repository) {
        super(repository, CategoryNotFoundException::byId);
    }
}
