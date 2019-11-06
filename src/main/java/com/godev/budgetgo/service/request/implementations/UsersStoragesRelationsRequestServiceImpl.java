package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsInfoDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.service.authorization.UsersStoragesRelationsAuthorizationService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.UsersStoragesRelationsDataService;
import com.godev.budgetgo.service.factory.UserStorageRelationsDtoFactory;
import com.godev.budgetgo.service.factory.UsersStoragesRelationsFactory;
import com.godev.budgetgo.service.merger.UsersStoragesRelationsMerger;
import com.godev.budgetgo.service.request.UsersStoragesRelationsRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class UsersStoragesRelationsRequestServiceImpl implements UsersStoragesRelationsRequestService {

    private final UsersStoragesRelationsDataService dataService;
    private final StoragesDataService storagesDataService;
    private final UsersStoragesRelationsFactory entitiesFactory;
    private final UserStorageRelationsDtoFactory dtoFactory;
    private final UsersStoragesRelationsMerger merger;
    private final UsersStoragesRelationsAuthorizationService authorizationService;
    private final StoragesAuthorizationService storagesAuthorizationService;

    public UsersStoragesRelationsRequestServiceImpl(
            UsersStoragesRelationsDataService dataService,
            StoragesDataService storagesDataService,
            UsersStoragesRelationsFactory entitiesFactory,
            UserStorageRelationsDtoFactory dtoFactory,
            UsersStoragesRelationsMerger merger,
            UsersStoragesRelationsAuthorizationService authorizationService,
            StoragesAuthorizationService storagesAuthorizationService
    ) {
        this.dataService = dataService;
        this.storagesDataService = storagesDataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
        this.authorizationService = authorizationService;
        this.storagesAuthorizationService = storagesAuthorizationService;
    }

    @Override
    public List<UserStorageRelationsInfoDto> getByStorageId(Long storageId) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return dataService
                .getByStorage(storage)
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Override
    public UserStorageRelationsInfoDto getById(UserStorageKey id) {
        UserStorageRelations entity = dataService.getById(id);
        storagesAuthorizationService.authorizeAccess(entity.getStorage());
        return dtoFactory.createFrom(entity);
    }

    @Transactional
    @Override
    public UserStorageRelationsInfoDto create(UserStorageRelationsCreationDto creationDto) {
        UserStorageRelations entity = entitiesFactory.createFrom(creationDto);
        authorizationService.authorizeCreation(entity);
        // TODO: Validation
        UserStorageRelations savedEntity = dataService.add(entity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public UserStorageRelationsInfoDto patch(UserStorageKey id, UserStorageRelationsPatchesDto patchesDto) {
        UserStorageRelations entity = dataService.getById(id);
        UserStorageRelations patchedEntity = merger.merge(patchesDto, entity);
        authorizationService.authorizeModification(entity, patchesDto);
        // TODO: Validation
        UserStorageRelations savedEntity = dataService.update(patchedEntity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public void deleteById(UserStorageKey id) {
        UserStorageRelations entity = dataService.getById(id);
        authorizationService.authorizeDeletionAccess(entity);
        dataService.delete(entity);
    }
}
