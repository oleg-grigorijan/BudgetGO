package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.entity.*;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import com.godev.budgetgo.exception.UserStorageRelationsAccessDeniedException;
import com.godev.budgetgo.service.authorization.UsersStoragesRelationsAuthorizationService;
import com.godev.budgetgo.service.data.UsersStoragesRelationsDataService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void authorizeDelete(UserStorageRelations entity) {
        if (!entity.getUser().getLogin().equals(authenticationFacade.getAuthentication().getName())) {
            UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();

            if (!entity.getUserRole().canBeModifiedBy(authUserRole)) {
                throw new UserStorageRelationsAccessDeniedException();
            }
        }
    }

    @Override
    public List<UserStorageRelations> getAllAuthorizedEntities() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void authorizeGet(UserStorageRelations entity) {
        if (!entity.getUser().getLogin().equals(authenticationFacade.getAuthentication().getName())) {
            getAuthenticatedUserRelations(entity.getStorage());
        }
    }

    @Override
    public void authorizeCreate(UserStorageRelations entity) {
        UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getStorage()).getUserRole();
        if (!entity.getUserRole().canBeCreatedBy(authUserRole)) {
            throw new UserStorageRelationsAccessDeniedException();
        }
    }

    @Override
    public void authorizePatch(UserStorageRelations entity, UserStorageRelations patchedEntity) {
        UserStorageRelations relations = getAuthenticatedUserRelations(entity.getStorage());
        UserStorageRole authUserRole = relations.getUserRole();
        if (patchedEntity.getUserRole() != null
                && (!entity.getUserRole().canBeModifiedBy(authUserRole)
                || !patchedEntity.getUserRole().canBeModifiedBy(authUserRole))) {
            throw new UserStorageRelationsAccessDeniedException();
        }
        if (entity.isIncludedInUserStatistics() != patchedEntity.isIncludedInUserStatistics()
                && !entity.getUser().getId().equals(relations.getUser().getId())) {
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
