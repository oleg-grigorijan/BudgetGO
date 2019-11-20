package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageOperationKey;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.service.converter.OperationsConverter;
import com.godev.budgetgo.service.data.OperationsDataService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.request.OperationsRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
class OperationsRequestServiceImpl implements OperationsRequestService {

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
        return converter.convertFromEntity(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OperationInfoDto> getByStorageId(Long storageId) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return converter.convertFromEntities(dataService.getByStorage(storage));
    }

    @Transactional(readOnly = true)
    @Override
    public List<OperationInfoDto> getByStorageIdAndDateBetween(Long storageId, LocalDate from, LocalDate to) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return converter.convertFromEntities(dataService.getByStorageAndDateBetween(storage, from, to));
    }

    @Transactional
    @Override
    public OperationInfoDto create(ExtendedOperationCreationDto creationDto) {
        Operation entity = converter.convertFromDto(creationDto);
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
        Operation savedEntity = dataService.add(entity);
        return converter.convertFromEntity(savedEntity);
    }

    @Transactional
    @Override
    public OperationInfoDto patch(StorageOperationKey id, OperationPatchesDto patchesDto) {
        Operation entity = dataService.getById(id);
        Operation patchedEntity = converter.merge(entity, patchesDto);
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
        Operation savedEntity = dataService.update(patchedEntity);
        return converter.convertFromEntity(savedEntity);
    }

    @Transactional
    @Override
    public void deleteById(StorageOperationKey id) {
        Operation entity = dataService.getById(id);
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
        dataService.delete(entity);
    }
}
