package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRole;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.UsersStoragesRelationsDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class StoragesAuthorizationServiceImpl implements StoragesAuthorizationService {

    private final AuthenticationFacade authenticationFacade;
    private final StoragesDataService storagesDataService;
    private final UsersStoragesRelationsDataService relationsDataService;

    public StoragesAuthorizationServiceImpl(
            AuthenticationFacade authenticationFacade,
            StoragesDataService storagesDataService,
            UsersStoragesRelationsDataService relationsDataService) {
        this.authenticationFacade = authenticationFacade;
        this.storagesDataService = storagesDataService;
        this.relationsDataService = relationsDataService;
    }

    @Override
    public List<Storage> getAllAuthorizedEntities() {
        return storagesDataService.getByUser(authenticationFacade.getAuthenticatedUser());
    }

    @Override
    public void authorizeGet(Storage entity) {
        User user = authenticationFacade.getAuthenticatedUser();
        relationsDataService
                .findById(new UserStorageKey(user.getId(), entity.getId()))
                .orElseThrow(StorageAccessDeniedException::new);
    }

    @Override
    public void authorizeCreate(StorageCreationDto creationDto) {
    }

    @Override
    public void authorizePatch(Storage entity, StoragePatchesDto patchesDto) {
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
