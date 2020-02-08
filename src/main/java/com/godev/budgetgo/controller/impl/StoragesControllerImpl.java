package com.godev.budgetgo.controller.impl;

import com.godev.budgetgo.controller.StoragesController;
import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.request.StoragesRequestService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class StoragesControllerImpl implements StoragesController {

    private final StoragesRequestService requestService;

    public StoragesControllerImpl(StoragesRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<StorageInfoDto> getAll() {
        return requestService.getAll();
    }

    @Override
    public StorageInfoDto create(HttpServletResponse response, @Valid StorageCreationDto creationDto) {
        StorageInfoDto createdDto = requestService.create(creationDto);
        response.addHeader("Location", "/api/storages/" + createdDto.getId());
        return createdDto;
    }

    @Override
    public StorageInfoDto getById(Long id) {
        return requestService.getById(id);
    }

    @Override
    public StorageInfoDto patchById(Long id, @Valid StoragePatchesDto patchesDto) {
        return requestService.patch(id, patchesDto);
    }
}
