package com.godev.budgetgo.request.impl;

import com.godev.budgetgo.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.converter.StoragesConverter;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.factory.StorageRelationsFactory;
import com.godev.budgetgo.request.StoragesRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoragesRequestServiceImpl implements StoragesRequestService {

    private final StoragesDataService dataService;
    private final StoragesRelationsDataService relationsDataService;
    private final StoragesConverter converter;
    private final StoragesAuthorizationService authorizationService;
    private final StorageRelationsFactory relationsFactory;

    public StoragesRequestServiceImpl(
            StoragesDataService dataService,
            StoragesRelationsDataService relationsDataService,
            StoragesConverter converter,
            StoragesAuthorizationService authorizationService,
            StorageRelationsFactory relationsFactory
    ) {
        this.dataService = dataService;
        this.relationsDataService = relationsDataService;
        this.converter = converter;
        this.authorizationService = authorizationService;
        this.relationsFactory = relationsFactory;
    }

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
