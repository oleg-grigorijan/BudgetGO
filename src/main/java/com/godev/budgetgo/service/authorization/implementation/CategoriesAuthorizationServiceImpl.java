package com.godev.budgetgo.service.authorization.implementation;

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
    public void authorizeCreate(Category entity) {

    }

    @Override
    public void authorizePatch(Category entity, Category patchedEntity) {

    }
}
