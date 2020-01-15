package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.request.StoragesRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/storages")
public class StoragesController {

    private final StoragesRequestService requestService;

    public StoragesController(StoragesRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StorageInfoDto> getAll() {
        return requestService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StorageInfoDto create(HttpServletResponse response, @RequestBody @Valid StorageCreationDto creationDto) {
        StorageInfoDto createdDto = requestService.create(creationDto);
        response.addHeader("Location", "/api/storages/" + createdDto.getId());
        return createdDto;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StorageInfoDto getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StorageInfoDto patch(@PathVariable Long id, @RequestBody @Valid StoragePatchesDto patchesDto) {
        return requestService.patch(id, patchesDto);
    }
}
