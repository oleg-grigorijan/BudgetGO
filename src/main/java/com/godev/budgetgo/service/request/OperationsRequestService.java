package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;

import java.time.LocalDate;
import java.util.List;

public interface OperationsRequestService {
    OperationInfoDto getById(Long id);

    List<OperationInfoDto> getByStorageId(Long storageId);

    List<OperationInfoDto> getByStorageIdAndDateBetween(Long storageId, LocalDate from, LocalDate to);

    OperationInfoDto create(OperationCreationDto creationDto);

    OperationInfoDto patch(Long id, OperationPatchesDto patchesDto);

    void deleteById(Long id);
}
