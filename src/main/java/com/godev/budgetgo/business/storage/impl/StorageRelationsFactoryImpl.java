package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.business.storage.StorageRelationsFactory;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageRelationsFactoryImpl implements StorageRelationsFactory {

    private final AuthenticationFacade authenticationFacade;

    @Override
    public StorageRelations createCreatorEntityForStorage(Storage storage) {
        StorageRelations e = new StorageRelations();
        e.setStorage(storage);
        User user = authenticationFacade.getAuthenticatedUser();
        e.setUser(user);
        e.setId(new UserStorageKey(user.getId(), storage.getId()));
        e.setUserRole(UserStorageRole.CREATOR);
        e.setIncludedInUserStatistics(true);
        e.setInviter(user);
        e.setInvitation(false);
        return e;
    }
}
