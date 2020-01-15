package com.godev.budgetgo.authorization.impl;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.authorization.UserStorageRoleAuthoritiesService;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRole;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoragesAuthorizationServiceImpl implements StoragesAuthorizationService {

    private final UserStorageRoleAuthoritiesService authoritiesService;
    private final AuthenticationFacade authenticationFacade;
    private final StoragesDataService storagesDataService;
    private final StoragesRelationsDataService relationsDataService;

    public StoragesAuthorizationServiceImpl(
            UserStorageRoleAuthoritiesService authoritiesService,
            AuthenticationFacade authenticationFacade,
            StoragesDataService storagesDataService,
            StoragesRelationsDataService relationsDataService) {
        this.authoritiesService = authoritiesService;
        this.authenticationFacade = authenticationFacade;
        this.storagesDataService = storagesDataService;
        this.relationsDataService = relationsDataService;
    }

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
