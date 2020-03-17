package com.godev.budgetgo.infra.authorization.impl;

import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.business.storage.StoragesRelationsDataService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import com.godev.budgetgo.infra.authorization.StoragesRelationsAuthorizationService;
import com.godev.budgetgo.infra.authorization.UserStorageRoleAuthoritiesService;
import com.godev.budgetgo.infra.error.exception.StorageAccessDeniedException;
import com.godev.budgetgo.infra.error.exception.StorageRelationsAccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoragesRelationsAuthorizationServiceImpl implements StoragesRelationsAuthorizationService {

    private final UserStorageRoleAuthoritiesService authoritiesService;

    private final AuthenticationFacade authenticationFacade;

    private final StoragesRelationsDataService relationsDataService;

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
