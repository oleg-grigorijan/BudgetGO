package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchDto;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.service.authorization.UsersStoragesRelationsAuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UsersStoragesRelationsAuthorizationServiceImpl implements UsersStoragesRelationsAuthorizationService {
    @Override
    public void authorizeDelete(UserStorageRelations entity) {

    }

    @Override
    public List<UserStorageRelations> getAllAuthorizedEntities() {
        return null;
    }

    @Override
    public void authorizeGet(UserStorageRelations entity) {

    }

    @Override
    public void authorizeCreate(UserStorageRelationsCreationDto creationDto) {

    }

    @Override
    public void authorizePatch(UserStorageRelations entity, UserStorageRelationsPatchDto patchesDto) {

    }
}
