package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRole;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.StoragesRelationsDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class StoragesAuthorizationServiceImpl implements StoragesAuthorizationService {

    private final AuthenticationFacade authenticationFacade;
    private final StoragesDataService storagesDataService;
    private final StoragesRelationsDataService relationsDataService;

    public StoragesAuthorizationServiceImpl(
            AuthenticationFacade authenticationFacade,
            StoragesDataService storagesDataService,
            StoragesRelationsDataService relationsDataService) {
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
        if (!role.canModifyStorage()) {
            throw new StorageAccessDeniedException();
        }
    }
}
