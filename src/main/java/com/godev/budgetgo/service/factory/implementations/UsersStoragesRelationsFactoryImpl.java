package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.entity.*;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.UsersDataService;
import com.godev.budgetgo.service.factory.UsersStoragesRelationsFactory;
import org.springframework.stereotype.Service;

@Service
class UsersStoragesRelationsFactoryImpl implements UsersStoragesRelationsFactory {

    private final StoragesDataService storagesDataService;
    private final UsersDataService usersDataService;
    private final AuthenticationFacade authenticationFacade;

    public UsersStoragesRelationsFactoryImpl(
            StoragesDataService storagesDataService,
            UsersDataService usersDataService,
            AuthenticationFacade authenticationFacade) {
        this.storagesDataService = storagesDataService;
        this.usersDataService = usersDataService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public UserStorageRelations createFrom(UserStorageRelationsCreationDto dto) {
        UserStorageRelations e = new UserStorageRelations();
        e.setStorage(storagesDataService.getById(dto.getStorageId()));
        e.setUser(usersDataService.getById(dto.getUserId()));
        e.setId(new UserStorageKey(dto.getUserId(), dto.getStorageId()));
        e.setUserRole(dto.getUserStorageRole());
        e.setIncludedInUserStatistics(getDefaultIncludedInUserStatistics(e.getUserRole()));
        return e;
    }

    /**
     * Creates an instance of {@link UserStorageRelations} with the given storage,
     * authenticated user and {@link UserStorageRole#CREATOR} role.
     * Other parameters are set by default.
     *
     * @param storage relations holder
     * @return created instance
     */
    @Override
    public UserStorageRelations generateCreatorEntityForStorage(Storage storage) {
        UserStorageRelations e = new UserStorageRelations();
        e.setStorage(storage);
        User user = authenticationFacade.getAuthenticatedUser();
        e.setUser(user);
        e.setId(new UserStorageKey(user.getId(), storage.getId()));
        e.setUserRole(UserStorageRole.CREATOR);
        e.setIncludedInUserStatistics(getDefaultIncludedInUserStatistics(e.getUserRole()));
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
