package com.godev.budgetgo.authorization;

import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;

public interface StoragesRelationsAuthorizationService {
    void authorizeCreation(StorageRelations entity);

    void authorizeModification(StorageRelations entity, StorageRelationsPatchesDto patchesDto);

    void authorizeDeletion(StorageRelations entity);
}
