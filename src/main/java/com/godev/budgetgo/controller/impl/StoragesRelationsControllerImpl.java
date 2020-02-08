package com.godev.budgetgo.controller.impl;

import com.godev.budgetgo.controller.StoragesRelationsController;
import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.request.StoragesRelationsRequestService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class StoragesRelationsControllerImpl implements StoragesRelationsController {

    private final StoragesRelationsRequestService requestService;

    public StoragesRelationsControllerImpl(StoragesRelationsRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<StorageRelationsInfoDto> getAll(Long storageId) {
        return requestService.getByStorageId(storageId);
    }

    @Override
    public StorageRelationsInfoDto create(HttpServletResponse response, Long storageId, @Valid StorageRelationsCreationDto creationDto) {
        StorageRelationsInfoDto createdDto = requestService.create(new ExtendedStorageRelationsCreationDto(creationDto, storageId));
        response.addHeader("Location", "/api/storages/" + storageId + "/users/" + creationDto.getUserId());
        return createdDto;
    }

    @Override
    public StorageRelationsInfoDto getById(Long storageId, Long userId) {
        return requestService.getById(new UserStorageKey(userId, storageId));
    }

    @Override
    public StorageRelationsInfoDto patchById(Long storageId, Long userId, @Valid StorageRelationsPatchesDto patchesDto) {
        return requestService.patch(new UserStorageKey(userId, storageId), patchesDto);
    }

    @Override
    public void deleteById(Long storageId, Long userId) {
        requestService.deleteById(new UserStorageKey(userId, storageId));
    }
}
