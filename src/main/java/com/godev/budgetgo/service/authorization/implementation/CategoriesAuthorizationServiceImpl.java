package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.service.authorization.CategoriesAuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CategoriesAuthorizationServiceImpl implements CategoriesAuthorizationService {
    @Override
    public List<Category> getAllAuthorizedEntities() {
        return null;
    }

    @Override
    public void authorizeGet(Category entity) {

    }

    @Override
    public void authorizeCreate(CategoryCreationDto creationDto) {

    }

    @Override
    public void authorizePatch(Category entity, CategoryPatchesDto patchesDto) {

    }
}
