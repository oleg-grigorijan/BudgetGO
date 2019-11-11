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
        dto.getCategoryId().ifPresent(
                categoryId -> e.setCategory(categoriesDataService.getById(categoryId))
        );
        dto.getMoneyDelta().ifPresent(e::setMoneyDelta);
        dto.getDate().ifPresent(e::setDate);
        dto.getDescription().ifPresent(e::setDescription);
        e.setDateModified(LocalDate.now());
        e.setLastEditor(authenticationFacade.getAuthenticatedUser());
        return e;
    }
}
