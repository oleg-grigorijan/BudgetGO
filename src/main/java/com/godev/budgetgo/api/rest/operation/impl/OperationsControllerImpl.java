package com.godev.budgetgo.api.rest.operation.impl;

import com.godev.budgetgo.api.rest.operation.OperationsController;
import com.godev.budgetgo.api.rest.operation.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationPatchesDto;
import com.godev.budgetgo.business.operation.OperationsRequestService;
import com.godev.budgetgo.domain.operation.StorageOperationKey;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OperationsControllerImpl implements OperationsController {

    private final OperationsRequestService requestService;

    @Override
    public List<OperationInfoDto> getAll(Long storageId) {
        return requestService.getByStorageId(storageId);
    }

    @Override
    public List<OperationInfoDto> getAllWithDateBetween(Long storageId, LocalDate dateFrom, LocalDate dateTo) {
        return requestService.getByStorageIdAndDateBetween(storageId, dateFrom, dateTo);
    }

    @Override
    public OperationInfoDto create(HttpServletResponse response, Long storageId, @Valid OperationCreationDto creationDto) {
        OperationInfoDto createdDto = requestService.create(new ExtendedOperationCreationDto(creationDto, storageId));
        response.addHeader("Location", "/api/storages/" + storageId + "/operations/" + createdDto.getId());
        return createdDto;
    }

    @Override
    public OperationInfoDto getById(Long storageId, Long operationId) {
        return requestService.getById(new StorageOperationKey(storageId, operationId));
    }

    @Override
    public OperationInfoDto patchById(Long storageId, Long operationId, @Valid OperationPatchesDto patchesDto) {
        return requestService.patch(new StorageOperationKey(storageId, operationId), patchesDto);
    }

    @Override
    public void deleteById(Long storageId, Long operationId) {
        requestService.deleteById(new StorageOperationKey(storageId, operationId));
    }
}
