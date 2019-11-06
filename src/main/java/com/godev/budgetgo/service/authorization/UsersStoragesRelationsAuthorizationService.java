package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.dto.UserStorageRelationsPatchesDto;
import com.godev.budgetgo.entity.UserStorageRelations;

public interface UsersStoragesRelationsAuthorizationService {
    void authorizeCreation(UserStorageRelations entity);

    void authorizeModification(UserStorageRelations entity, UserStorageRelationsPatchesDto patchesDto);

    void authorizeDeletionAccess(UserStorageRelations entity);
}
