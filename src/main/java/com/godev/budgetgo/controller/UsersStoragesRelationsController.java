package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsInfoDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchDto;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.service.request.UsersStoragesRelationsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/storages/{storageId}/users")
public class UsersStoragesRelationsController {

    private final UsersStoragesRelationsRequestService relationsService;

    public UsersStoragesRelationsController(UsersStoragesRelationsRequestService relationsService) {
        this.relationsService = relationsService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserStorageRelationsInfoDto> getAll(@PathVariable Long storageId) {
        return relationsService.getByStorageId(storageId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            HttpServletResponse response,
            @PathVariable Long storageId,
            @RequestBody UserStorageRelationsCreationDto creationDto
    ) {
        creationDto.setStorageId(storageId);
        relationsService.create(creationDto);
        response.addHeader(
                "Location",
                "/api/storages/" + storageId
                        + "/users/" + creationDto.getUserId()
        );
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserStorageRelationsInfoDto get(
            @PathVariable Long storageId,
            @PathVariable Long userId
    ) {
        return relationsService.getById(new UserStorageKey(userId, storageId));
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserStorageRelationsInfoDto patch(
            @PathVariable Long storageId,
            @PathVariable Long userId,
            @RequestBody UserStorageRelationsPatchDto patchDto
    ) {
        return relationsService.patch(
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
        relationsService.deleteById(new UserStorageKey(userId, storageId));
    }
}
