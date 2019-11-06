package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.authorization.OperationsAuthorizationService;
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
class OperationsRequestServiceImpl
        extends AbstractRequestService<Operation, Long, OperationInfoDto, OperationCreationDto, OperationPatchesDto>
        implements OperationsRequestService {

    private final OperationsDataService dataService;
    private final OperationDtoFactory dtoFactory;
    private final StoragesDataService storagesDataService;
    private final OperationsAuthorizationService authorizationService;

    public OperationsRequestServiceImpl(
            OperationsDataService dataService,
            OperationsFactory entitiesFactory,
            OperationDtoFactory dtoFactory,
            OperationsMerger merger,
            OperationsAuthorizationService authorizationService,
            StoragesDataService storagesDataService) {
        super(dataService, entitiesFactory, dtoFactory, merger, authorizationService);
        this.dataService = dataService;
        this.dtoFactory = dtoFactory;
        this.storagesDataService = storagesDataService;
        this.authorizationService = authorizationService;
    }

    @Override
    public List<OperationInfoDto> getByStorageId(Long storageId) {
        Storage storage = storagesDataService.getById(storageId);
        return authorizationService
                .getAuthorizedEntitiesByStorage(storage)
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationInfoDto> getByStorageIdAndDateBetween(Long storageId, LocalDate from, LocalDate to) {
        Storage storage = storagesDataService.getById(storageId);
        return authorizationService
                .getAuthorizedEntitiesByStorageAndDateBetween(storage, from, to)
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Operation entity = dataService.getById(id);
        authorizationService.authorizeDelete(entity);
        dataService.delete(entity);
    }
}
