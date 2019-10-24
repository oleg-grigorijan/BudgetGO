package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.factory.StorageDtoFactory;
import com.godev.budgetgo.service.factory.StoragesFactory;
import com.godev.budgetgo.service.merger.StoragesMerger;
import com.godev.budgetgo.service.request.StoragesRequestService;
import org.springframework.stereotype.Service;

@Service
class StoragesRequestServiceImpl
        extends AbstractRequestService<Storage, Long, StorageInfoDto, StorageCreationDto, StoragePatchesDto>
        implements StoragesRequestService {

    public StoragesRequestServiceImpl(
            StoragesDataService dataService,
            StoragesFactory entitiesFactory,
            StorageDtoFactory dtoFactory,
            StoragesMerger merger
    ) {
        super(dataService, entitiesFactory, dtoFactory, merger);
    }
}