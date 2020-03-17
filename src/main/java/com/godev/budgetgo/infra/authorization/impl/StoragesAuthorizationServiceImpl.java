package com.godev.budgetgo.infra.authorization.impl;

import com.godev.budgetgo.business.storage.StoragesDataService;
import com.godev.budgetgo.business.storage.StoragesRelationsDataService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import com.godev.budgetgo.infra.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.infra.authorization.UserStorageRoleAuthoritiesService;
import com.godev.budgetgo.infra.error.exception.StorageAccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoragesAuthorizationServiceImpl implements StoragesAuthorizationService {

    private final UserStorageRoleAuthoritiesService authoritiesService;

    private final AuthenticationFacade authenticationFacade;

    private final StoragesDataService storagesDataService;

    private final StoragesRelationsDataService relationsDataService;

    @Override
    public List<Storage> getAllAuthorizedEntities() {
        return storagesDataService.getByUser(authenticationFacade.getAuthenticatedUser());
    }

    @Override
    public void authorizeAccess(Storage entity) {
        User user = authenticationFacade.getAuthenticatedUser();
        relationsDataService
                .findById(new UserStorageKey(user.getId(), entity.getId()))
                .orElseThrow(StorageAccessDeniedException::new);
    }

    @Override
    public void authorizeModificationAccess(Storage entity) {
        User user = authenticationFacade.getAuthenticatedUser();
        UserStorageRole role = relationsDataService
                .findById(new UserStorageKey(user.getId(), entity.getId()))
                .orElseThrow(StorageAccessDeniedException::new)
                .getUserRole();
        if (!authoritiesService.canModifyStorage(role)) {
            throw new StorageAccessDeniedException();
        }
    }
}
