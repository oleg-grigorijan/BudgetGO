package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.service.factory.CategoryDtoFactory;
import org.springframework.stereotype.Service;

@Service
class CategoryDtoFactoryImpl implements CategoryDtoFactory {
    @Override
    public CategoryInfoDto createFrom(Category e) {
        CategoryInfoDto dto = new CategoryInfoDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        return dto;
    }
}
