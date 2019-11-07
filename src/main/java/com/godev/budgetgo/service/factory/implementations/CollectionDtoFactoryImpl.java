package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.CollectionInfoDto;
import com.godev.budgetgo.entity.Collection;
import com.godev.budgetgo.service.factory.CategoryDtoFactory;
import com.godev.budgetgo.service.factory.CollectionDtoFactory;
import org.springframework.stereotype.Service;

@Service
class CollectionDtoFactoryImpl implements CollectionDtoFactory {

    private final CategoryDtoFactory categoryDtoFactory;

    public CollectionDtoFactoryImpl(CategoryDtoFactory categoryDtoFactory) {
        this.categoryDtoFactory = categoryDtoFactory;
    }

    @Override
    public CollectionInfoDto createFrom(Collection e) {
        CollectionInfoDto dto = new CollectionInfoDto();
        dto.setCategoryInfoDto(categoryDtoFactory.createFrom(e.getCategory()));
        return dto;
    }
}
