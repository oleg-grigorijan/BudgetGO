package com.godev.budgetgo.request.impl;

import com.godev.budgetgo.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.authorization.StoragesRelationsAuthorizationService;
import com.godev.budgetgo.converter.StorageRelationsConverter;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.request.StoragesRelationsRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class StoragesRelationsRequestServiceImpl implements StoragesRelationsRequestService {

    private final StoragesRelationsDataService dataService;
    private final StoragesDataService storagesDataService;
    private final StorageRelationsConverter converter;
    private final StoragesRelationsAuthorizationService authorizationService;
    private final StoragesAuthorizationService storagesAuthorizationService;

    public StoragesRelationsRequestServiceImpl(
            StoragesRelationsDataService dataService,
            StoragesDataService storagesDataService,
            StorageRelationsConverter converter,
            StoragesRelationsAuthorizationService authorizationService,
            StoragesAuthorizationService storagesAuthorizationService
    ) {
        this.dataService = dataService;
        this.storagesDataService = storagesDataService;
        this.converter = converter;
        this.authorizationService = authorizationService;
        this.storagesAuthorizationService = storagesAuthorizationService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<StorageRelationsInfoDto> getByStorageId(Long storageId) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return converter.convertFromEntities(dataService.getByStorage(storage));
    }

    @Transactional(readOnly = true)
    @Override
    public StorageRelationsInfoDto getById(UserStorageKey id) {
        StorageRelations entity = dataService.getById(id);
        return converter.convertFromEntity(entity);
    }

    @Transactional
    @Override
    public StorageRelationsInfoDto create(ExtendedStorageRelationsCreationDto creationDto) {
        StorageRelations entity = converter.convertFromDto(creationDto);
        authorizationService.authorizeCreation(entity);
        StorageRelations savedEntity = dataService.add(entity);
        return converter.convertFromEntity(savedEntity);
    }

    @Transactional
    @Override
    public StorageRelationsInfoDto patch(UserStorageKey id, StorageRelationsPatchesDto patchesDto) {
        StorageRelations entity = dataService.getById(id);
        StorageRelations patchedEntity = converter.merge(entity, patchesDto);
        authorizationService.authorizeModification(entity, patchesDto);
        StorageRelations savedEntity = dataService.update(patchedEntity);
        return converter.convertFromEntity(savedEntity);
    }

    @Transactional
    @Override
    public void deleteById(UserStorageKey id) {
        StorageRelations entity = dataService.getById(id);
        authorizationService.authorizeDeletionAccess(entity);
        dataService.delete(entity);
    }
}
