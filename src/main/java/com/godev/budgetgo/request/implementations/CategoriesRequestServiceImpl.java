package com.godev.budgetgo.request.implementations;

import com.godev.budgetgo.converter.CategoriesConverter;
import com.godev.budgetgo.data.CategoriesDataService;
import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.request.CategoriesRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class CategoriesRequestServiceImpl implements CategoriesRequestService {

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
        return converter.convertFromEntity(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryInfoDto> getAll() {
        return converter.convertFromEntities(dataService.getAll());

    }

    @Transactional
    @Override
    public CategoryInfoDto create(CategoryCreationDto creationDto) {
        Category entity = converter.convertFromDto(creationDto);
        Category savedEntity = dataService.add(entity);
        return converter.convertFromEntity(savedEntity);
    }

    @Transactional
    @Override
    public CategoryInfoDto patch(Long id, CategoryPatchesDto patchesDto) {
        Category entity = dataService.getById(id);
        Category patchedEntity = converter.merge(entity, patchesDto);
        Category savedEntity = dataService.update(patchedEntity);
        return converter.convertFromEntity(savedEntity);
    }
}
