package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.data.OperationsDataService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.factory.OperationDtoFactory;
import com.godev.budgetgo.service.factory.OperationsFactory;
import com.godev.budgetgo.service.merger.OperationsMerger;
import com.godev.budgetgo.service.request.OperationsRequestService;
import org.springframework.stereotype.Service;

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

    public OperationsRequestServiceImpl(
            OperationsDataService dataService,
            OperationsFactory entitiesFactory,
            OperationDtoFactory dtoFactory,
            OperationsMerger merger,
            StoragesDataService storagesDataService) {
        super(dataService, entitiesFactory, dtoFactory, merger);
        this.dataService = dataService;
        this.dtoFactory = dtoFactory;
        this.storagesDataService = storagesDataService;
    }

    @Override
    public List<OperationInfoDto> getByDateBetween(LocalDate from, LocalDate to) {
        return dataService.getByDateBetween(from, to)
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationInfoDto> getByStorageIdAndDateBetween(Long storageId, LocalDate from, LocalDate to) {
        Storage storage = storagesDataService.getById(storageId);
        return dataService.getByStorageAndDateBetween(storage, from, to)
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Operation entity = dataService.getById(id);
        dataService.delete(entity);
    }
}
