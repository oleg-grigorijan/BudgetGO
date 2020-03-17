package com.godev.budgetgo.business.operation.impl;

import com.godev.budgetgo.api.rest.operation.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationPatchesDto;
import com.godev.budgetgo.business.operation.OperationsConverter;
import com.godev.budgetgo.business.operation.OperationsDataService;
import com.godev.budgetgo.business.operation.OperationsRequestService;
import com.godev.budgetgo.business.storage.StoragesDataService;
import com.godev.budgetgo.domain.operation.Operation;
import com.godev.budgetgo.domain.operation.StorageOperationKey;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.infra.authorization.StoragesAuthorizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperationsRequestServiceImpl implements OperationsRequestService {

    private final OperationsDataService dataService;
    private final OperationsConverter converter;
    private final StoragesDataService storagesDataService;
    private final StoragesAuthorizationService storagesAuthorizationService;

    public OperationsRequestServiceImpl(
            OperationsDataService dataService,
            StoragesDataService storagesDataService,
            OperationsConverter converter,
            StoragesAuthorizationService storagesAuthorizationService
    ) {
        this.dataService = dataService;
        this.storagesDataService = storagesDataService;
        this.converter = converter;
        this.storagesAuthorizationService = storagesAuthorizationService;
    }

    @Transactional(readOnly = true)
    @Override
    public OperationInfoDto getById(StorageOperationKey id) {
        Operation entity = dataService.getById(id);
        storagesAuthorizationService.authorizeAccess(entity.getStorage());
        return converter.convertToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OperationInfoDto> getByStorageId(Long storageId) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return converter.convertToDtos(dataService.getByStorage(storage));
    }

    @Transactional(readOnly = true)
    @Override
    public List<OperationInfoDto> getByStorageIdAndDateBetween(Long storageId, LocalDate from, LocalDate to) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return converter.convertToDtos(dataService.getByStorageAndDateBetween(storage, from, to));
    }

    @Transactional
    @Override
    public OperationInfoDto create(ExtendedOperationCreationDto creationDto) {
        Operation entity = converter.convertToEntity(creationDto);
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
        Operation savedEntity = dataService.add(entity);
        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public OperationInfoDto patch(StorageOperationKey id, OperationPatchesDto patchesDto) {
        Operation entity = dataService.getById(id);
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
        Operation patchedEntity = converter.merge(entity, patchesDto);
        Operation savedEntity = dataService.update(patchedEntity);
        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public void deleteById(StorageOperationKey id) {
        Operation entity = dataService.getById(id);
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
        dataService.delete(entity);
    }
}
