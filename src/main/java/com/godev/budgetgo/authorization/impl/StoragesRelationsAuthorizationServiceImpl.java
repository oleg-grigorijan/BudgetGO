package com.godev.budgetgo.authorization.impl;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.authorization.StoragesRelationsAuthorizationService;
import com.godev.budgetgo.authorization.UserStorageRoleAuthoritiesService;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRole;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import com.godev.budgetgo.exception.StorageRelationsAccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class StoragesRelationsAuthorizationServiceImpl implements StoragesRelationsAuthorizationService {

    private final UserStorageRoleAuthoritiesService authoritiesService;
    private final AuthenticationFacade authenticationFacade;
    private final StoragesRelationsDataService relationsDataService;

    public StoragesRelationsAuthorizationServiceImpl(
            UserStorageRoleAuthoritiesService authoritiesService,
            AuthenticationFacade authenticationFacade,
            StoragesRelationsDataService relationsDataService
    ) {
        this.authoritiesService = authoritiesService;
        this.authenticationFacade = authenticationFacade;
        this.relationsDataService = relationsDataService;
    }

    @Override
    public void authorizeCreationAccess(StorageRelations entity) {
        UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
        if (!authoritiesService.canBeCreatedBy(entity.getUserRole(), authUserRole)) {
            throw new StorageRelationsAccessDeniedException();
        }
    }

    @Override
    public void authorizeModificationAccess(StorageRelations entity, StorageRelationsPatchesDto patchesDto) {
        UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
        if (!authoritiesService.canBeModifiedBy(entity.getUserRole(), authUserRole)) {
            throw new StorageRelationsAccessDeniedException();
        }
        patchesDto.getUserRole().ifPresent(userRole -> {
            if (!authoritiesService.canBeCreatedBy(userRole, authUserRole)) {
                throw new StorageRelationsAccessDeniedException();
            }
        });
    }

    @Override
    public void authorizeDeletionAccess(StorageRelations entity) {
        if (!entity.getUser().getId().equals(authenticationFacade.getAuthenticatedUser().getId())) {
            UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
            if (!authoritiesService.canBeModifiedBy(entity.getUserRole(), authUserRole)) {
                throw new StorageRelationsAccessDeniedException();
            }
        }
    }

    private StorageRelations getAuthenticatedUserRelations(Storage storage) {
        User user = authenticationFacade.getAuthenticatedUser();
        return relationsDataService
                .findById(new UserStorageKey(user.getId(), storage.getId()))
                .orElseThrow(StorageAccessDeniedException::new);
    }
}
