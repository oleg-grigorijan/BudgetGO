package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.service.data.CategoriesDataService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.factory.OperationsFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
class OperationsFactoryImpl implements OperationsFactory {
    private final CategoriesDataService categoriesDataService;
    private final StoragesDataService storagesDataService;

    public OperationsFactoryImpl(
            CategoriesDataService categoriesDataService,
            StoragesDataService storagesDataService) {
        this.categoriesDataService = categoriesDataService;
        this.storagesDataService = storagesDataService;
    }

    @Override
    public Operation createFrom(OperationCreationDto dto) {
        Operation e = new Operation();
        e.setStorage(storagesDataService.getById(dto.getStorageId()));
        e.setCategory(categoriesDataService.getById(dto.getCategoryId()));
        e.setMoneyDelta(dto.getMoneyDelta());
        e.setDate(dto.getDate());
        e.setDescription(dto.getDescription());
        e.setDateCreated(LocalDate.now());
        e.setDateModified(LocalDate.now());
        return e;
    }
}
