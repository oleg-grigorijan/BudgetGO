package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.service.merger.StoragesRelationsMerger;
import org.springframework.stereotype.Service;

@Service
class StoragesRelationsMergerImpl implements StoragesRelationsMerger {
    @Override
    public StorageRelations merge(StorageRelationsPatchesDto dto, StorageRelations eOld) {
        StorageRelations e = eOld.clone();
        dto.getUserRole().ifPresent(e::setUserRole);
        return e;
    }
}
