package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.business.storage.StorageSettingsConverter;
import com.godev.budgetgo.business.storage.StorageSettingsRequestService;
import com.godev.budgetgo.business.storage.StoragesRelationsDataService;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StorageSettingsRequestServiceImpl implements StorageSettingsRequestService {

    private final StoragesRelationsDataService dataService;

    private final StorageSettingsConverter converter;

    private final AuthenticationFacade authenticationFacade;

    @Transactional(readOnly = true)
    @Override
    public StorageSettingsInfoDto getByStorageId(Long storageId) {
        User user = authenticationFacade.getAuthenticatedUser();
        StorageRelations entity = dataService.getById(new UserStorageKey(user.getId(), storageId));
        return converter.convertToDto(entity);
    }

    @Transactional
    @Override
    public StorageSettingsInfoDto patch(Long storageId, StorageSettingsPatchesDto patchesDto) {
        User user = authenticationFacade.getAuthenticatedUser();
        StorageRelations entity = dataService.getById(new UserStorageKey(user.getId(), storageId));
        StorageRelations patchedEntity = converter.merge(entity, patchesDto);
        StorageRelations savedEntity = dataService.update(patchedEntity);
        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public void deleteByStorageId(Long storageId) {
        User user = authenticationFacade.getAuthenticatedUser();
        StorageRelations entity = dataService.getById(new UserStorageKey(user.getId(), storageId));
        dataService.delete(entity);
    }
}
