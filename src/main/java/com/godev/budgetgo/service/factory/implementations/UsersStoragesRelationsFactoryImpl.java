package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.UsersDataService;
import com.godev.budgetgo.service.factory.UsersStoragesRelationsFactory;
import org.springframework.stereotype.Service;

@Service
class UsersStoragesRelationsFactoryImpl implements UsersStoragesRelationsFactory {

    private final StoragesDataService storagesDataService;
    private final UsersDataService usersDataService;

    public UsersStoragesRelationsFactoryImpl(
            StoragesDataService storagesDataService,
            UsersDataService usersDataService) {
        this.storagesDataService = storagesDataService;
        this.usersDataService = usersDataService;
    }

    @Override
    public UserStorageRelations createFrom(UserStorageRelationsCreationDto dto) {
        UserStorageRelations e = new UserStorageRelations();
        e.setStorage(storagesDataService.getById(dto.getStorageId()));
        e.setUser(usersDataService.getById(dto.getUserId()));
        e.setId(new UserStorageKey(dto.getUserId(), dto.getStorageId()));
        e.setUserRole(dto.getUserRole());
        switch (dto.getUserRole()) {
            case READER:
                e.setIncludedInUserStatistics(false);
                break;
            case CREATOR:
            case ADMIN:
            case EDITOR:
                e.setIncludedInUserStatistics(true);
                break;
            default:
                throw new RuntimeException("No handler for UserStorageRole " + dto.getUserRole().getValue());
        }
        return e;
    }
}
