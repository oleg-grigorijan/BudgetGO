package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.service.data.CategoriesDataService;
import com.godev.budgetgo.service.merger.OperationsMerger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
class OperationsMergerImpl implements OperationsMerger {

    private final CategoriesDataService categoriesDataService;
    private final AuthenticationFacade authenticationFacade;

    public OperationsMergerImpl(
            CategoriesDataService categoriesDataService,
            AuthenticationFacade authenticationFacade) {
        this.categoriesDataService = categoriesDataService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Operation merge(OperationPatchesDto dto, Operation eOld) {
        Operation e = eOld.clone();
        if (dto.getCategoryId() != null) {
            e.setCategory(categoriesDataService.getById(dto.getCategoryId()));
        }
        if (dto.getMoneyDelta() != null) e.setMoneyDelta(dto.getMoneyDelta());
        if (dto.getDate() != null) e.setDate(dto.getDate());
        if (dto.getDescription() != null) e.setDescription(dto.getDescription());
        e.setDateModified(LocalDate.now());
        e.setLastEditor(authenticationFacade.getAuthenticatedUser());
        return e;
    }
}
