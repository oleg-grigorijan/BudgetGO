package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.api.rest.storage.dto.StorageCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StoragePatchesDto;
import com.godev.budgetgo.business.storage.StorageRelationsFactory;
import com.godev.budgetgo.business.storage.StoragesConverter;
import com.godev.budgetgo.business.storage.StoragesDataService;
import com.godev.budgetgo.business.storage.StoragesRelationsDataService;
import com.godev.budgetgo.business.storage.StoragesRequestService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.infra.authorization.StoragesAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoragesRequestServiceImpl implements StoragesRequestService {

    private final StoragesDataService dataService;

    private final StoragesRelationsDataService relationsDataService;

    private final StoragesConverter converter;

    private final StoragesAuthorizationService authorizationService;

    private final StorageRelationsFactory relationsFactory;

    @Transactional(readOnly = true)
    @Override
    public StorageInfoDto getById(Long id) {
        Storage entity = dataService.getById(id);
        authorizationService.authorizeAccess(entity);
        return converter.convertToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StorageInfoDto> getAll() {
        return converter.convertToDtos(authorizationService.getAllAuthorizedEntities());
    }

    @Transactional
    @Override
    public StorageInfoDto create(StorageCreationDto creationDto) {
        Storage entity = converter.convertToEntity(creationDto);
        Storage savedEntity = dataService.add(entity);

        StorageRelations creatorRelations = relationsFactory.createCreatorEntityForStorage(savedEntity);
        relationsDataService.add(creatorRelations);

        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public StorageInfoDto patch(Long id, StoragePatchesDto patchesDto) {
        Storage entity = dataService.getById(id);
        authorizationService.authorizeModificationAccess(entity);
        Storage patchedEntity = converter.merge(entity, patchesDto);
        Storage savedEntity = dataService.update(patchedEntity);
        return converter.convertToDto(savedEntity);
    }
}
