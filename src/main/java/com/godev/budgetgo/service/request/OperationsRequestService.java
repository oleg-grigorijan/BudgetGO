package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;

import java.time.LocalDate;
import java.util.List;

public interface OperationsRequestService
        extends RequestService<Long, OperationInfoDto, OperationCreationDto, OperationPatchesDto> {

    List<OperationInfoDto> getByStorageId(Long storageId);

    List<OperationInfoDto> getByStorageIdAndDateBetween(Long storageId, LocalDate from, LocalDate to);

    void deleteById(Long id);
}
