package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.service.merger.StoragesSettingsMerger;
import org.springframework.stereotype.Service;

@Service
class StoragesSettingsMergerImpl implements StoragesSettingsMerger {
    @Override
    public StorageRelations merge(StorageSettingsPatchesDto dto, StorageRelations eOld) {
        StorageRelations e = eOld.clone();
        if (dto.getIncludedInUserStatistics() != null) e.setIncludedInUserStatistics(dto.getIncludedInUserStatistics());
        if (dto.getInvitation() != null) e.setInvitation(dto.getInvitation());
        return e;
    }
}
