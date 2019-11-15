package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.StorageOperationKey;
import com.godev.budgetgo.exception.BadRequestException;
import com.godev.budgetgo.service.request.OperationsRequestService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/storages/{storageId}/operations")
public class OperationsController {

    private final OperationsRequestService requestService;

    public OperationsController(OperationsRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OperationInfoDto> getAll(
            @PathVariable Long storageId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo
    ) {
        if (dateFrom == null && dateTo == null) {
            return requestService.getByStorageId(storageId);
        }
        if (dateFrom != null && dateTo != null) {
            return requestService.getByStorageIdAndDateBetween(storageId, dateFrom, dateTo);
        }
        throw new BadRequestException();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            HttpServletResponse response,
            @PathVariable Long storageId,
            @RequestBody @Valid OperationCreationDto creationDto
    ) {
        Long newOperationId = requestService
                .create(new ExtendedOperationCreationDto(creationDto, storageId))
                .getId();
        response.addHeader(
                "Location",
                "/api/storages/" + storageId
                        + "/operations/" + newOperationId
        );
    }

    @GetMapping("/{operationId}")
    @ResponseStatus(HttpStatus.OK)
    public OperationInfoDto getById(
            @PathVariable Long storageId,
            @PathVariable Long operationId
    ) {
        return requestService.getById(new StorageOperationKey(storageId, operationId));
    }

    @PatchMapping("/{operationId}")
    @ResponseStatus(HttpStatus.OK)
    public OperationInfoDto patch(
            @PathVariable Long storageId,
            @PathVariable Long operationId,
            @RequestBody @Valid OperationPatchesDto patchesDto
    ) {
        return requestService.patch(
                new StorageOperationKey(storageId, operationId),
                patchesDto
        );
    }

    @DeleteMapping("/{operationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long storageId,
            @PathVariable Long operationId
    ) {
        requestService.deleteById(new StorageOperationKey(storageId, operationId));
    }
}
