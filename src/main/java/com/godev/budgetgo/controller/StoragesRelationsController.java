package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.request.StoragesRelationsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/storages/{storageId}/users")
public class StoragesRelationsController {

    private final StoragesRelationsRequestService requestService;

    public StoragesRelationsController(StoragesRelationsRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StorageRelationsInfoDto> getAll(@PathVariable Long storageId) {
        return requestService.getByStorageId(storageId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StorageRelationsInfoDto create(
            HttpServletResponse response,
            @PathVariable Long storageId,
            @RequestBody @Valid StorageRelationsCreationDto creationDto
    ) {
        StorageRelationsInfoDto createdDto = requestService.create(new ExtendedStorageRelationsCreationDto(creationDto, storageId));
        response.addHeader("Location", "/api/storages/" + storageId + "/users/" + creationDto.getUserId());
        return createdDto;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public StorageRelationsInfoDto get(@PathVariable Long storageId, @PathVariable Long userId) {
        return requestService.getById(new UserStorageKey(userId, storageId));
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public StorageRelationsInfoDto patch(
            @PathVariable Long storageId,
            @PathVariable Long userId,
            @RequestBody @Valid StorageRelationsPatchesDto patchesDto
    ) {
        return requestService.patch(new UserStorageKey(userId, storageId), patchesDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long storageId, @PathVariable Long userId) {
        requestService.deleteById(new UserStorageKey(userId, storageId));
    }
}
