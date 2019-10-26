package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.service.factory.CategoriesFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoriesFactoryImpl implements CategoriesFactory {
    @Override
    public Category createFrom(CategoryCreationDto dto) {
        Category e = new Category();
        e.setName(dto.getName());
        return e;
    }
}
