package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.service.factory.CategoryDtoFactory;
import com.godev.budgetgo.service.factory.OperationDtoFactory;
import com.godev.budgetgo.service.factory.StorageDtoFactory;
import org.springframework.stereotype.Service;

@Service
class OperationDtoFactoryImpl implements OperationDtoFactory {
    private final StorageDtoFactory storageDtoFactory;
    private final CategoryDtoFactory categoryDtoFactory;

    public OperationDtoFactoryImpl(
            StorageDtoFactory storageDtoFactory,
            CategoryDtoFactory categoryDtoFactory) {
        this.storageDtoFactory = storageDtoFactory;
        this.categoryDtoFactory = categoryDtoFactory;
    }

    @Override
    public OperationInfoDto createFrom(Operation e) {
        OperationInfoDto dto = new OperationInfoDto();
        dto.setId(e.getId());
        dto.setStorage(storageDtoFactory.createFrom(e.getStorage()));
        dto.setCategory(categoryDtoFactory.createFrom(e.getCategory()));
        dto.setMoneyDelta(e.getMoneyDelta());
        dto.setDate(e.getDate());
        dto.setDescription(e.getDescription());
        dto.setDateCreated(e.getDateCreated());
        dto.setDateModified(e.getDateModified());
        return dto;
    }
}
