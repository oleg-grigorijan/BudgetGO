package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.service.converter.StorageSettingsConverter;
import com.godev.budgetgo.service.data.StoragesRelationsDataService;
import com.godev.budgetgo.service.request.StorageSettingsRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class StorageSettingsRequestServiceImpl implements StorageSettingsRequestService {

    private final StoragesRelationsDataService dataService;
    private final StorageSettingsConverter converter;
    private final AuthenticationFacade authenticationFacade;

    public StorageSettingsRequestServiceImpl(
            StoragesRelationsDataService dataService,
            StorageSettingsConverter converter,
            AuthenticationFacade authenticationFacade
    ) {
        this.dataService = dataService;
        this.converter = converter;
        this.authenticationFacade = authenticationFacade;
    }

    @Transactional(readOnly = true)
    @Override
    public StorageSettingsInfoDto getByStorageId(Long storageId) {
        User user = authenticationFacade.getAuthenticatedUser();
        StorageRelations entity = dataService.getById(new UserStorageKey(user.getId(), storageId));
        return converter.convertFromEntity(entity);
    }

    @Transactional
    @Override
    public StorageSettingsInfoDto patch(Long storageId, StorageSettingsPatchesDto patchesDto) {
        User user = authenticationFacade.getAuthenticatedUser();
        StorageRelations entity = dataService.getById(new UserStorageKey(user.getId(), storageId));
        StorageRelations patchedEntity = converter.merge(entity, patchesDto);
        StorageRelations savedEntity = dataService.update(patchedEntity);
        return converter.convertFromEntity(savedEntity);
    }
}
