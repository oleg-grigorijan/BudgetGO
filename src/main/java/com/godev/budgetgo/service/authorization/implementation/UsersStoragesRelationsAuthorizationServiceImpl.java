package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.entity.UserStorageRole;
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
            UserStorageRole authUserRole = getAuthenticatedUserRelations(entity.getId().getStorageId()).getUserRole();

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
            getAuthenticatedUserRelations(entity.getId().getStorageId());
        }
    }

    @Override
    public void authorizeCreate(UserStorageRelationsCreationDto creationDto) {
        UserStorageRole authUserRole = getAuthenticatedUserRelations(creationDto.getStorageId()).getUserRole();
        if (!creationDto.getUserStorageRole().canBeCreatedBy(authUserRole)) {
            throw new UserStorageRelationsAccessDeniedException();
        }
    }

    @Override
    public void authorizePatch(UserStorageRelations entity, UserStorageRelationsPatchDto patchesDto) {
        UserStorageRelations relations = getAuthenticatedUserRelations(entity.getId().getStorageId());
        UserStorageRole authUserRole = relations.getUserRole();
        if (patchesDto.getUserStorageRole() != null
                && (!entity.getUserRole().canBeModifiedBy(authUserRole)
                || !patchesDto.getUserStorageRole().canBeModifiedBy(authUserRole))) {
            throw new UserStorageRelationsAccessDeniedException();
        }
        if (patchesDto.getIncludedInUserStatistics() != null
                && !entity.getUser().getId().equals(relations.getUser().getId())) {
            throw new UserStorageRelationsAccessDeniedException();
        }
    }

    private UserStorageRelations getAuthenticatedUserRelations(Long storageId) {
        User user = authenticationFacade.getAuthenticatedUser();
        return relationsDataService
                .findById(new UserStorageKey(user.getId(), storageId))
                .orElseThrow(StorageAccessDeniedException::new);
    }
}
