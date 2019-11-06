package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.exception.BadRequestException;
import com.godev.budgetgo.service.request.OperationsRequestService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/operations")
@Secured("ROLE_USER")
public class OperationsController {

    private final OperationsRequestService requestService;

    public OperationsController(OperationsRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OperationInfoDto> getByDate(
            @RequestParam Long storageId,
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
    public void create(HttpServletResponse response, @RequestBody OperationCreationDto creationDto) {
        Long newOperationId = requestService.create(creationDto).getId();
        response.addHeader("Location", "/api/operations/" + newOperationId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationInfoDto getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationInfoDto patch(@PathVariable Long id, @RequestBody OperationPatchesDto patches) {
        return requestService.patch(id, patches);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        requestService.deleteById(id);
    }
}
