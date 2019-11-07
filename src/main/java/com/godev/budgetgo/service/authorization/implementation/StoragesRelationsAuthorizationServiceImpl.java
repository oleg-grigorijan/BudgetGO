package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.*;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import com.godev.budgetgo.exception.StorageRelationsAccessDeniedException;
import com.godev.budgetgo.service.authorization.StoragesRelationsAuthorizationService;
import com.godev.budgetgo.service.data.StoragesRelationsDataService;
import org.springframework.stereotype.Service;

@Service
class StoragesRelationsAuthorizationServiceImpl implements StoragesRelationsAuthorizationService {

    private final AuthenticationFacade authenticationFacade;
    private final StoragesRelationsDataService relationsDataService;

    public StoragesRelationsAuthorizationServiceImpl(
            AuthenticationFacade authenticationFacade,
            StoragesRelationsDataService relationsDataService
    ) {
        this.authenticationFacade = authenticationFacade;
        this.relationsDataService = relationsDataService;
    }

    @Override
    public void authorizeDeletionAccess(StorageRelations entity) {
        if (!entity.getUser().getLogin().equals(authenticationFacade.getAuthentication().getName())) {
            UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
            if (!entity.getUserRole().canBeModifiedBy(authUserRole)) {
                throw new StorageRelationsAccessDeniedException();
            }
        }
    }

    @Override
    public void authorizeCreation(StorageRelations entity) {
        UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
        if (!entity.getUserRole().canBeCreatedBy(authUserRole)) {
            throw new StorageRelationsAccessDeniedException();
        }
    }

    @Override
    public void authorizeModification(StorageRelations entity, StorageRelationsPatchesDto patchesDto) {
        UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
        if (!entity.getUserRole().canBeModifiedBy(authUserRole)) {
            throw new StorageRelationsAccessDeniedException();
        }
        if (patchesDto.getUserRole() != null
                && !patchesDto.getUserRole().canBeCreatedBy(authUserRole)) {
            throw new StorageRelationsAccessDeniedException();
        }
    }

    private StorageRelations getAuthenticatedUserRelations(Storage storage) {
        User user = authenticationFacade.getAuthenticatedUser();
        return relationsDataService
                .findById(new UserStorageKey(user.getId(), storage.getId()))
                .orElseThrow(StorageAccessDeniedException::new);
    }
}
