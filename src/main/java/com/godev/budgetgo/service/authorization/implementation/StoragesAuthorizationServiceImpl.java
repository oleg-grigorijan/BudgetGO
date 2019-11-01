package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class StoragesAuthorizationServiceImpl implements StoragesAuthorizationService {
    @Override
    public List<Storage> getAllAuthorizedEntities() {
        return null;
    }

    @Override
    public void authorizeGet(Storage entity) {

    }

    @Override
    public void authorizeCreate(StorageCreationDto creationDto) {

    }

    @Override
    public void authorizePatch(Storage entity, StoragePatchesDto patchesDto) {

    }
}
