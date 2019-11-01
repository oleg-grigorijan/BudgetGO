package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;

public interface StoragesAuthorizationService
        extends AuthorizationService<Storage, StorageCreationDto, StoragePatchesDto> {
}
