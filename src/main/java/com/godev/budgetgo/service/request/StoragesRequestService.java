package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;

public interface StoragesRequestService
        extends RequestService<Long, StorageInfoDto, StorageCreationDto, StoragePatchesDto> {
}