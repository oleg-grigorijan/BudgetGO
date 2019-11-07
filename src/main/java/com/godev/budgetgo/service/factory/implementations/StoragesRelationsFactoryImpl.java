package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.StorageRelationsCreationDto;
import com.godev.budgetgo.entity.*;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.UsersDataService;
import com.godev.budgetgo.service.factory.StoragesRelationsFactory;
import org.springframework.stereotype.Service;

@Service
class StoragesRelationsFactoryImpl implements StoragesRelationsFactory {

    private final StoragesDataService storagesDataService;
    private final UsersDataService usersDataService;
    private final AuthenticationFacade authenticationFacade;

    public StoragesRelationsFactoryImpl(
            StoragesDataService storagesDataService,
            UsersDataService usersDataService,
            AuthenticationFacade authenticationFacade) {
        this.storagesDataService = storagesDataService;
        this.usersDataService = usersDataService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public StorageRelations createFrom(StorageRelationsCreationDto dto) {
        StorageRelations e = new StorageRelations();
        e.setStorage(storagesDataService.getById(dto.getStorageId()));
        e.setUser(usersDataService.getById(dto.getUserId()));
        e.setId(new UserStorageKey(dto.getUserId(), dto.getStorageId()));
        e.setUserRole(dto.getUserRole());
        e.setIncludedInUserStatistics(getDefaultIncludedInUserStatistics(e.getUserRole()));
        e.setInviter(authenticationFacade.getAuthenticatedUser());
        e.setInvitation(true);
        return e;
    }

    /**
     * Creates an instance of {@link StorageRelations} with the given storage,
     * authenticated user and {@link UserStorageRole#CREATOR} role.
     * Other parameters are set by default.
     *
     * @param storage relations holder
     * @return created instance
     */
    @Override
    public StorageRelations generateCreatorEntityForStorage(Storage storage) {
        StorageRelations e = new StorageRelations();
        e.setStorage(storage);
        User user = authenticationFacade.getAuthenticatedUser();
        e.setUser(user);
        e.setId(new UserStorageKey(user.getId(), storage.getId()));
        e.setUserRole(UserStorageRole.CREATOR);
        e.setIncludedInUserStatistics(getDefaultIncludedInUserStatistics(e.getUserRole()));
        e.setInviter(user);
        e.setInvitation(false);
        return e;
    }

    private boolean getDefaultIncludedInUserStatistics(UserStorageRole userRole) {
        if (userRole == UserStorageRole.READER) {
            return false;
        } else if (userRole == UserStorageRole.CREATOR
                || userRole == UserStorageRole.ADMIN
                || userRole == UserStorageRole.EDITOR) {
            return true;
        } else {
            throw new RuntimeException("No handler for UserStorageRole " + userRole.getValue());
        }
    }
}
