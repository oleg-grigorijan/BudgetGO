package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.StorageOperationKey;

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
