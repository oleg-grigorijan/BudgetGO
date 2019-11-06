package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.entity.UserStorageRelations;

public interface UsersStoragesRelationsAuthorizationService
        extends AuthorizationService<UserStorageRelations> {

    void authorizeDelete(UserStorageRelations entity);
}
