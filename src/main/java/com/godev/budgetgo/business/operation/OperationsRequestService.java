package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.api.rest.operation.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationPatchesDto;
import com.godev.budgetgo.domain.operation.StorageOperationKey;

import java.time.LocalDate;
import java.util.List;

public interface OperationsRequestService {

    OperationInfoDto getById(StorageOperationKey id);

    List<OperationInfoDto> getByStorageId(Long storageId);

    List<OperationInfoDto> getByStorageIdAndDateBetween(Long storageId, LocalDate from, LocalDate to);

    OperationInfoDto create(ExtendedOperationCreationDto creationDto);

    OperationInfoDto patch(StorageOperationKey id, OperationPatchesDto patchesDto);

    void deleteById(StorageOperationKey id);
}
