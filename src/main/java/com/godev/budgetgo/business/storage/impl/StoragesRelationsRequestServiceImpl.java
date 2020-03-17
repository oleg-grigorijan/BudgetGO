package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.api.rest.storage.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.business.storage.StorageRelationsConverter;
import com.godev.budgetgo.business.storage.StoragesDataService;
import com.godev.budgetgo.business.storage.StoragesRelationsDataService;
import com.godev.budgetgo.business.storage.StoragesRelationsRequestService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.infra.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.infra.authorization.StoragesRelationsAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoragesRelationsRequestServiceImpl implements StoragesRelationsRequestService {

    private final StoragesRelationsDataService dataService;

    private final StoragesDataService storagesDataService;

    private final StorageRelationsConverter converter;

    private final StoragesRelationsAuthorizationService authorizationService;

    private final StoragesAuthorizationService storagesAuthorizationService;

    @Transactional(readOnly = true)
    @Override
    public List<StorageRelationsInfoDto> getByStorageId(Long storageId) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return converter.convertToDtos(dataService.getByStorage(storage));
    }

    @Transactional(readOnly = true)
    @Override
    public StorageRelationsInfoDto getById(UserStorageKey id) {
        StorageRelations entity = dataService.getById(id);
        return converter.convertToDto(entity);
    }

    @Transactional
    @Override
    public StorageRelationsInfoDto create(ExtendedStorageRelationsCreationDto creationDto) {
        StorageRelations entity = converter.convertToEntity(creationDto);
        authorizationService.authorizeCreationAccess(entity);
        StorageRelations savedEntity = dataService.add(entity);
        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public StorageRelationsInfoDto patch(UserStorageKey id, StorageRelationsPatchesDto patchesDto) {
        StorageRelations entity = dataService.getById(id);
        StorageRelations patchedEntity = converter.merge(entity, patchesDto);
        authorizationService.authorizeModificationAccess(entity, patchesDto);
        StorageRelations savedEntity = dataService.update(patchedEntity);
        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public void deleteById(UserStorageKey id) {
        StorageRelations entity = dataService.getById(id);
        authorizationService.authorizeDeletionAccess(entity);
        dataService.delete(entity);
    }
}
