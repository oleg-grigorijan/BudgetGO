package com.godev.budgetgo.api.rest.storage.impl;

import com.godev.budgetgo.api.rest.storage.StoragesController;
import com.godev.budgetgo.api.rest.storage.dto.StorageCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StoragePatchesDto;
import com.godev.budgetgo.business.storage.StoragesRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoragesControllerImpl implements StoragesController {

    private final StoragesRequestService requestService;

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
