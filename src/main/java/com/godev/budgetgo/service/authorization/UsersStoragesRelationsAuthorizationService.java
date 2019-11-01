package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchDto;
import com.godev.budgetgo.entity.UserStorageRelations;

public interface UsersStoragesRelationsAuthorizationService
        extends AuthorizationService<UserStorageRelations, UserStorageRelationsCreationDto, UserStorageRelationsPatchDto> {

    void authorizeDelete(UserStorageRelations entity);
}
