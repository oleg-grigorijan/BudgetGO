package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
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
    private final AuthenticationFacade authenticationFacade;

    public OperationsFactoryImpl(
            CategoriesDataService categoriesDataService,
            StoragesDataService storagesDataService,
            AuthenticationFacade authenticationFacade) {
        this.categoriesDataService = categoriesDataService;
        this.storagesDataService = storagesDataService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Operation createFrom(ExtendedOperationCreationDto dto) {
        Operation e = new Operation();
        e.setStorage(storagesDataService.getById(dto.getStorageId()));
        e.setCategory(categoriesDataService.getById(dto.getCategoryId()));
        e.setMoneyDelta(dto.getMoneyDelta());
        e.setDate(dto.getDate());
        e.setDescription(dto.getDescription());
        e.setDateCreated(LocalDate.now());
        e.setDateModified(LocalDate.now());
        e.setCreator(authenticationFacade.getAuthenticatedUser());
        e.setLastEditor(e.getCreator());
        return e;
    }
}
