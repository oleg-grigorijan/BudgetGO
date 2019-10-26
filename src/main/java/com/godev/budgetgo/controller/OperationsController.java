package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.service.request.OperationsRequestService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationsController {

    private final OperationsRequestService operationsService;

    public OperationsController(OperationsRequestService storagesService) {
        this.operationsService = storagesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OperationInfoDto> getByDate(
            @RequestParam(required = false) Long storageId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo
    ) {
        if (storageId == null) {
            return operationsService.getByDateBetween(dateFrom, dateTo);
        } else {
            return operationsService.getByStorageIdAndDateBetween(storageId, dateFrom, dateTo);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HttpServletResponse response, @RequestBody OperationCreationDto creationDto) {
        Long newOperationId = operationsService.create(creationDto).getId();
        response.addHeader("Location", "/api/operations/" + newOperationId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationInfoDto getById(@PathVariable Long id) {
        return operationsService.getById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationInfoDto patch(@PathVariable Long id, @RequestBody OperationPatchesDto patches) {
        return operationsService.patch(id, patches);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        operationsService.deleteById(id);
    }
}
