package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.service.authorization.StoragesRelationsAuthorizationService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.StoragesRelationsDataService;
import com.godev.budgetgo.service.factory.StorageRelationsDtoFactory;
import com.godev.budgetgo.service.factory.StoragesRelationsFactory;
import com.godev.budgetgo.service.merger.StoragesRelationsMerger;
import com.godev.budgetgo.service.request.StoragesRelationsRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class StoragesRelationsRequestServiceImpl implements StoragesRelationsRequestService {

    private final StoragesRelationsDataService dataService;
    private final StoragesDataService storagesDataService;
    private final StoragesRelationsFactory entitiesFactory;
    private final StorageRelationsDtoFactory dtoFactory;
    private final StoragesRelationsMerger merger;
    private final StoragesRelationsAuthorizationService authorizationService;
    private final StoragesAuthorizationService storagesAuthorizationService;

    public StoragesRelationsRequestServiceImpl(
            StoragesRelationsDataService dataService,
            StoragesDataService storagesDataService,
            StoragesRelationsFactory entitiesFactory,
            StorageRelationsDtoFactory dtoFactory,
            StoragesRelationsMerger merger,
            StoragesRelationsAuthorizationService authorizationService,
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
    public List<StorageRelationsInfoDto> getByStorageId(Long storageId) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return dataService
                .getByStorage(storage)
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Override
    public StorageRelationsInfoDto getById(UserStorageKey id) {
        StorageRelations entity = dataService.getById(id);
        return dtoFactory.createFrom(entity);
    }

    @Transactional
    @Override
    public StorageRelationsInfoDto create(ExtendedStorageRelationsCreationDto creationDto) {
        StorageRelations entity = entitiesFactory.createFrom(creationDto);
        authorizationService.authorizeCreation(entity);
        // TODO: Validation
        StorageRelations savedEntity = dataService.add(entity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public StorageRelationsInfoDto patch(UserStorageKey id, StorageRelationsPatchesDto patchesDto) {
        StorageRelations entity = dataService.getById(id);
        StorageRelations patchedEntity = merger.merge(patchesDto, entity);
        authorizationService.authorizeModification(entity, patchesDto);
        // TODO: Validation
        StorageRelations savedEntity = dataService.update(patchedEntity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public void deleteById(UserStorageKey id) {
        StorageRelations entity = dataService.getById(id);
        authorizationService.authorizeDeletionAccess(entity);
        dataService.delete(entity);
    }
}
