package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.service.data.OperationsDataService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.factory.OperationDtoFactory;
import com.godev.budgetgo.service.factory.OperationsFactory;
import com.godev.budgetgo.service.merger.OperationsMerger;
import com.godev.budgetgo.service.request.OperationsRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
class OperationsRequestServiceImpl implements OperationsRequestService {

    private final OperationsDataService dataService;
    private final OperationsFactory entitiesFactory;
    private final OperationDtoFactory dtoFactory;
    private final OperationsMerger merger;
    private final StoragesDataService storagesDataService;
    private final StoragesAuthorizationService storagesAuthorizationService;

    public OperationsRequestServiceImpl(
            OperationsDataService dataService,
            StoragesDataService storagesDataService,
            OperationsFactory entitiesFactory,
            OperationDtoFactory dtoFactory,
            OperationsMerger merger,
            StoragesAuthorizationService storagesAuthorizationService
    ) {
        this.dataService = dataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
        this.storagesDataService = storagesDataService;
        this.storagesAuthorizationService = storagesAuthorizationService;
    }

    @Transactional(readOnly = true)
    @Override
    public OperationInfoDto getById(Long id) {
        Operation entity = dataService.getById(id);
        storagesAuthorizationService.authorizeAccess(entity.getStorage());
        return dtoFactory.createFrom(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OperationInfoDto> getByStorageId(Long storageId) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return dataService
                .getByStorage(storage)
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<OperationInfoDto> getByStorageIdAndDateBetween(Long storageId, LocalDate from, LocalDate to) {
        Storage storage = storagesDataService.getById(storageId);
        storagesAuthorizationService.authorizeAccess(storage);
        return dataService
                .getByStorageAndDateBetween(storage, from, to)
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OperationInfoDto create(OperationCreationDto creationDto) {
        Operation entity = entitiesFactory.createFrom(creationDto);
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
        Operation savedEntity = dataService.add(entity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public OperationInfoDto patch(Long id, OperationPatchesDto patchesDto) {
        Operation entity = dataService.getById(id);
        Operation patchedEntity = merger.merge(patchesDto, entity);
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
        Operation savedEntity = dataService.update(patchedEntity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Operation entity = dataService.getById(id);
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
        dataService.delete(entity);
    }
}
