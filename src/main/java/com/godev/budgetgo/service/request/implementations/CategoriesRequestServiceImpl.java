package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.service.data.CategoriesDataService;
import com.godev.budgetgo.service.factory.CategoriesFactory;
import com.godev.budgetgo.service.factory.CategoryDtoFactory;
import com.godev.budgetgo.service.merger.CategoriesMerger;
import com.godev.budgetgo.service.request.CategoriesRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CategoriesRequestServiceImpl
        implements CategoriesRequestService {

    private final CategoriesDataService dataService;
    private final CategoriesFactory entitiesFactory;
    private final CategoryDtoFactory dtoFactory;
    private final CategoriesMerger merger;

    public CategoriesRequestServiceImpl(
            CategoriesDataService dataService,
            CategoriesFactory entitiesFactory,
            CategoryDtoFactory dtoFactory,
            CategoriesMerger merger
    ) {
        this.dataService = dataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryInfoDto getById(Long id) {
        Category entity = dataService.getById(id);
        return dtoFactory.createFrom(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryInfoDto> getAll() {
        return dataService
                .getAll()
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CategoryInfoDto create(CategoryCreationDto creationDto) {
        Category entity = entitiesFactory.createFrom(creationDto);
        Category savedEntity = dataService.add(entity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public CategoryInfoDto patch(Long id, CategoryPatchesDto patchesDto) {
        Category entity = dataService.getById(id);
        Category patchedEntity = merger.merge(patchesDto, entity);
        Category savedEntity = dataService.update(patchedEntity);
        return dtoFactory.createFrom(savedEntity);
    }
}
