package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.StorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.service.request.StoragesRelationsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/storages/{storageId}/users")
@Secured("ROLE_USER")
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
    public void create(
            HttpServletResponse response,
            @PathVariable Long storageId,
            @RequestBody @Valid StorageRelationsCreationDto creationDto
    ) {
        creationDto.setStorageId(storageId);
        requestService.create(creationDto);
        response.addHeader(
                "Location",
                "/api/storages/" + storageId
                        + "/users/" + creationDto.getUserId()
        );
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public StorageRelationsInfoDto get(
            @PathVariable Long storageId,
            @PathVariable Long userId
    ) {
        return requestService.getById(new UserStorageKey(userId, storageId));
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public StorageRelationsInfoDto patch(
            @PathVariable Long storageId,
            @PathVariable Long userId,
            @RequestBody @Valid StorageRelationsPatchesDto patchDto
    ) {
        return requestService.patch(
                new UserStorageKey(userId, storageId),
                patchDto
        );
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long storageId,
            @PathVariable Long userId
    ) {
        requestService.deleteById(new UserStorageKey(userId, storageId));
    }
}
