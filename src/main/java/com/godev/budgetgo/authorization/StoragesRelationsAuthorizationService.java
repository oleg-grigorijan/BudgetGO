package com.godev.budgetgo.authorization;

import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;

public interface StoragesRelationsAuthorizationService {
    void authorizeCreationAccess(StorageRelations entity);

    void authorizeModificationAccess(StorageRelations entity, StorageRelationsPatchesDto patchesDto);

    void authorizeDeletionAccess(StorageRelations entity);
}
