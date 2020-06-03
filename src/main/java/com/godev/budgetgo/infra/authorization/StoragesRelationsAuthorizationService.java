package com.godev.budgetgo.infra.authorization;

import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.domain.storage.StorageRelations;

public interface StoragesRelationsAuthorizationService {

    void authorizeCreationAccess(StorageRelations entity);

    void authorizeModificationAccess(StorageRelations entity, StorageRelationsPatchesDto patchesDto);

    void authorizeDeletionAccess(StorageRelations entity);
}
