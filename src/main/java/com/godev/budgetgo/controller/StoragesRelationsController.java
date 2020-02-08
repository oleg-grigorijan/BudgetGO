package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.StorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Storage users")
@RequestMapping("/api/storages/{storageId}/users")
public interface StoragesRelationsController {

    @ApiOperation("Returns all users that have access to storage specified by id")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<StorageRelationsInfoDto> getAll(@PathVariable Long storageId);

    @ApiOperation("Invites user to storage specified by id")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    StorageRelationsInfoDto create(HttpServletResponse response, @PathVariable Long storageId, @RequestBody StorageRelationsCreationDto creationDto);

    @ApiOperation("Finds user specified by id with access to storage specified by id")
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    StorageRelationsInfoDto getById(@PathVariable Long storageId, @PathVariable Long userId);

    @ApiOperation("Patches storage user")
    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    StorageRelationsInfoDto patchById(@PathVariable Long storageId, @PathVariable Long userId, @RequestBody StorageRelationsPatchesDto patchesDto);

    @ApiOperation("Removes user specified by id from storage specified by id")
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long storageId, @PathVariable Long userId);
}
