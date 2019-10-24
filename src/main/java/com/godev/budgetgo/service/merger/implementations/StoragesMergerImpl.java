package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.merger.StoragesMerger;
import org.springframework.stereotype.Service;

@Service
class StoragesMergerImpl implements StoragesMerger {
    @Override
    public void merge(StoragePatchesDto dto, Storage e) {
        if (dto.getName() != null) e.setName(dto.getName());
        if (dto.getDescription() != null) e.setDescription(dto.getDescription());
    }
}
