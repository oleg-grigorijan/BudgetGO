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

@Service
public class CategoriesRequestServiceImpl
        extends AbstractRequestService<Category, Long, CategoryInfoDto, CategoryCreationDto, CategoryPatchesDto>
        implements CategoriesRequestService {

    public CategoriesRequestServiceImpl(
            CategoriesDataService dataService,
            CategoriesFactory entitiesFactory,
            CategoryDtoFactory dtoFactory,
            CategoriesMerger merger) {
        super(dataService, entitiesFactory, dtoFactory, merger);
    }
}
