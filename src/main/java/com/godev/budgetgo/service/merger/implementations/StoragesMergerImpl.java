package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.merger.StoragesMerger;
import org.springframework.stereotype.Service;

@Service
class StoragesMergerImpl implements StoragesMerger {
    @Override
    public Storage merge(StoragePatchesDto dto, Storage eOld) {
        Storage e = eOld.clone();
        dto.getName().ifPresent(e::setName);
        dto.getDescription().ifPresent(e::setDescription);
        return e;
    }
}
