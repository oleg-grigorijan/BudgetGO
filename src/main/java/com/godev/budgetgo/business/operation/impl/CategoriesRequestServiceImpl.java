package com.godev.budgetgo.business.operation.impl;

import com.godev.budgetgo.api.rest.operation.dto.CategoryCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryPatchesDto;
import com.godev.budgetgo.business.operation.CategoriesConverter;
import com.godev.budgetgo.business.operation.CategoriesDataService;
import com.godev.budgetgo.business.operation.CategoriesRequestService;
import com.godev.budgetgo.domain.operation.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriesRequestServiceImpl implements CategoriesRequestService {

    private final CategoriesDataService dataService;
    private final CategoriesConverter converter;

    public CategoriesRequestServiceImpl(CategoriesDataService dataService, CategoriesConverter converter) {
        this.dataService = dataService;
        this.converter = converter;
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryInfoDto getById(Long id) {
        Category entity = dataService.getById(id);
        return converter.convertToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryInfoDto getByName(String name) {
        Category entity = dataService.getByName(name);
        return converter.convertToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryInfoDto> getAll() {
        return converter.convertToDtos(dataService.getAll());

    }

    @Transactional
    @Override
    public CategoryInfoDto create(CategoryCreationDto creationDto) {
        Category entity = converter.convertToEntity(creationDto);
        Category savedEntity = dataService.add(entity);
        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public CategoryInfoDto patch(Long id, CategoryPatchesDto patchesDto) {
        Category entity = dataService.getById(id);
        Category patchedEntity = converter.merge(entity, patchesDto);
        Category savedEntity = dataService.update(patchedEntity);
        return converter.convertToDto(savedEntity);
    }
}
