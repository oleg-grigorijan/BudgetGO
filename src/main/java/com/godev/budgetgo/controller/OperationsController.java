package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.StorageOperationKey;
import com.godev.budgetgo.request.OperationsRequestService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public List<OperationInfoDto> getAll(@PathVariable Long storageId) {
        return requestService.getByStorageId(storageId);
    }

    @GetMapping(params = {"dateFrom", "dateTo"})
    @ResponseStatus(HttpStatus.OK)
    public List<OperationInfoDto> getAllWithDateBetween(
            @PathVariable Long storageId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo
    ) {
        return requestService.getByStorageIdAndDateBetween(storageId, dateFrom, dateTo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationInfoDto create(HttpServletResponse response, @PathVariable Long storageId, @RequestBody @Valid OperationCreationDto creationDto) {
        OperationInfoDto createdDto = requestService.create(new ExtendedOperationCreationDto(creationDto, storageId));
        response.addHeader("Location", "/api/storages/" + storageId + "/operations/" + createdDto.getId());
        return createdDto;
    }

    @GetMapping("/{operationId}")
    @ResponseStatus(HttpStatus.OK)
    public OperationInfoDto getById(@PathVariable Long storageId, @PathVariable Long operationId) {
        return requestService.getById(new StorageOperationKey(storageId, operationId));
    }

    @PatchMapping("/{operationId}")
    @ResponseStatus(HttpStatus.OK)
    public OperationInfoDto patch(@PathVariable Long storageId, @PathVariable Long operationId, @RequestBody @Valid OperationPatchesDto patchesDto) {
        return requestService.patch(new StorageOperationKey(storageId, operationId), patchesDto);
    }

    @DeleteMapping("/{operationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long storageId, @PathVariable Long operationId) {
        requestService.deleteById(new StorageOperationKey(storageId, operationId));
    }
}
