package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.UserStorageRelationsPatchDto;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.service.merger.UsersStoragesRelationsMerger;
import org.springframework.stereotype.Service;

@Service
class UsersStoragesRelationsMergerImpl implements UsersStoragesRelationsMerger {
    @Override
    public UserStorageRelations merge(UserStorageRelationsPatchDto dto, UserStorageRelations eOld) {
        UserStorageRelations e = eOld.clone();
        if (dto.getUserStorageRole() != null) e.setUserRole(dto.getUserStorageRole());
        if (dto.getIncludedInUserStatistics() != null) e.setIncludedInUserStatistics(dto.getIncludedInUserStatistics());
        return e;
    }
}
