package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.UserStorageRelationsPatchesDto;
import com.godev.budgetgo.entity.*;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import com.godev.budgetgo.exception.UserStorageRelationsAccessDeniedException;
import com.godev.budgetgo.service.authorization.UsersStoragesRelationsAuthorizationService;
import com.godev.budgetgo.service.data.UsersStoragesRelationsDataService;
import org.springframework.stereotype.Service;

@Service
class UsersStoragesRelationsAuthorizationServiceImpl implements UsersStoragesRelationsAuthorizationService {

    private final AuthenticationFacade authenticationFacade;
    private final UsersStoragesRelationsDataService relationsDataService;

    public UsersStoragesRelationsAuthorizationServiceImpl(
            AuthenticationFacade authenticationFacade,
            UsersStoragesRelationsDataService relationsDataService
    ) {
        this.authenticationFacade = authenticationFacade;
        this.relationsDataService = relationsDataService;
    }

    @Override
    public void authorizeDeletionAccess(UserStorageRelations entity) {
        if (!entity.getUser().getLogin().equals(authenticationFacade.getAuthentication().getName())) {
            UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
            if (!entity.getUserRole().canBeModifiedBy(authUserRole)) {
                throw new UserStorageRelationsAccessDeniedException();
            }
        }
    }

    @Override
    public void authorizeCreation(UserStorageRelations entity) {
        UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
        if (!entity.getUserRole().canBeCreatedBy(authUserRole)) {
            throw new UserStorageRelationsAccessDeniedException();
        }
    }

    @Override
    public void authorizeModification(UserStorageRelations entity, UserStorageRelationsPatchesDto patchesDto) {
        UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
        if (!entity.getUserRole().canBeModifiedBy(authUserRole)) {
            throw new UserStorageRelationsAccessDeniedException();
        }
        if (patchesDto.getUserRole() != null
                && !patchesDto.getUserRole().canBeCreatedBy(authUserRole)) {
            throw new UserStorageRelationsAccessDeniedException();
        }
    }

    private UserStorageRelations getAuthenticatedUserRelations(Storage storage) {
        User user = authenticationFacade.getAuthenticatedUser();
        return relationsDataService
                .findById(new UserStorageKey(user.getId(), storage.getId()))
                .orElseThrow(StorageAccessDeniedException::new);
    }
}
