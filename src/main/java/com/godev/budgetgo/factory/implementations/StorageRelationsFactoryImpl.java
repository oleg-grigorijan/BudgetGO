package com.godev.budgetgo.factory.implementations;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.entity.*;
import com.godev.budgetgo.factory.StorageRelationsFactory;
import org.springframework.stereotype.Service;

@Service
class StorageRelationsFactoryImpl implements StorageRelationsFactory {

    private final AuthenticationFacade authenticationFacade;

    public StorageRelationsFactoryImpl(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public StorageRelations createCreatorEntityForStorage(Storage storage) {
        StorageRelations e = new StorageRelations();
        e.setStorage(storage);
        User user = authenticationFacade.getAuthenticatedUser();
        e.setUser(user);
        e.setId(new UserStorageKey(user.getId(), storage.getId()));
        e.setUserRole(UserStorageRole.CREATOR);
        e.setIncludedInUserStatistics(e.getUserRole().getDefaultIncludedInUserStatistics());
        e.setInviter(user);
        e.setInvitation(false);
        return e;
    }
}
