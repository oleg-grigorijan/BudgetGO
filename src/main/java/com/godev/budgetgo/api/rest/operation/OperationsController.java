package com.godev.budgetgo.api.rest.operation;

import com.godev.budgetgo.api.rest.operation.dto.OperationCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationPatchesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

@Api(tags = "Operations")
@RequestMapping("/api/storages/{storageId}/operations")
public interface OperationsController {

    @ApiOperation("Returns all operations in storage specified by id")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<OperationInfoDto> getAll(@PathVariable Long storageId);

    @ApiOperation("Returns all operations in storage specified by id filtered by date")
    @GetMapping(params = {"dateFrom", "dateTo"})
    @ResponseStatus(HttpStatus.OK)
    List<OperationInfoDto> getAllWithDateBetween(
            @PathVariable Long storageId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo
    );

    @ApiOperation("Creates operation in storage specified by id")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OperationInfoDto create(HttpServletResponse response, @PathVariable Long storageId, @RequestBody OperationCreationDto creationDto);

    @ApiOperation("Finds operation by id in storage specified by id")
    @GetMapping("/{operationId}")
    @ResponseStatus(HttpStatus.OK)
    OperationInfoDto getById(@PathVariable Long storageId, @PathVariable Long operationId);

    @ApiOperation("Patches operation found by id in storage specified by id")
    @PatchMapping("/{operationId}")
    @ResponseStatus(HttpStatus.OK)
    OperationInfoDto patchById(@PathVariable Long storageId, @PathVariable Long operationId, @RequestBody OperationPatchesDto patchesDto);

    @ApiOperation("Deletes operation found by id in storage specified by id")
    @DeleteMapping("/{operationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long storageId, @PathVariable Long operationId);
}
