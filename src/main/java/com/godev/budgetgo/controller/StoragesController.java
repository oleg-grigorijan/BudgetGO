package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Storages")
@RequestMapping("/api/storages")
public interface StoragesController {

    @ApiOperation("Returns all storages of authorized user")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<StorageInfoDto> getAll();

    @ApiOperation("Creates storage and adds authorized user there")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    StorageInfoDto create(HttpServletResponse response, @RequestBody StorageCreationDto creationDto);

    @ApiOperation("Finds storage by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    StorageInfoDto getById(@PathVariable Long id);

    @ApiOperation("Patches storage found by id")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    StorageInfoDto patchById(@PathVariable Long id, @RequestBody StoragePatchesDto patchesDto);
}
