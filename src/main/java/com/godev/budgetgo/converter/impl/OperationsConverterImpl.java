package com.godev.budgetgo.converter.impl;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.CategoriesConverter;
import com.godev.budgetgo.converter.OperationsConverter;
import com.godev.budgetgo.converter.UsersConverter;
import com.godev.budgetgo.data.CategoriesDataService;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.exception.NotFoundException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
class OperationsConverterImpl implements OperationsConverter {

    private final StoragesDataService storagesDataService;
    private final CategoriesDataService categoriesDataService;
    private final AuthenticationFacade authenticationFacade;
    private final CategoriesConverter categoriesConverter;
    private final UsersConverter usersConverter;

    public OperationsConverterImpl(
            StoragesDataService storagesDataService,
            CategoriesDataService categoriesDataService,
            AuthenticationFacade authenticationFacade,
            CategoriesConverter categoriesConverter,
            UsersConverter usersConverter
    ) {
        this.storagesDataService = storagesDataService;
        this.categoriesDataService = categoriesDataService;
        this.authenticationFacade = authenticationFacade;
        this.categoriesConverter = categoriesConverter;
        this.usersConverter = usersConverter;
    }

    @Override
    public Operation convertFromDto(ExtendedOperationCreationDto dto) {
        try {
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

        } catch (NotFoundException ex) {
            throw new UnprocessableEntityException(ex);
        }
    }

    @Override
    public OperationInfoDto convertFromEntity(Operation e) {
        OperationInfoDto dto = new OperationInfoDto();
        dto.setId(e.getId().getOperationId());
        dto.setCategoryInfoDto(categoriesConverter.convertFromEntity(e.getCategory()));
        dto.setMoneyDelta(e.getMoneyDelta());
        dto.setDate(e.getDate());
        dto.setDescription(e.getDescription());
        dto.setDateCreated(e.getDateCreated());
        dto.setDateModified(e.getDateModified());
        dto.setLastEditorInfoDto(usersConverter.convertFromEntity(e.getLastEditor()));
        dto.setCreatorInfoDto(usersConverter.convertFromEntity(e.getCreator()));
        return dto;
    }

    @Override
    public Operation merge(Operation eOld, OperationPatchesDto dto) {
        try {
            Operation e = eOld.clone();
            dto.getCategoryId()
                    .ifPresent(categoryId -> e.setCategory(categoriesDataService.getById(categoryId)));
            dto.getMoneyDelta().ifPresent(e::setMoneyDelta);
            dto.getDate().ifPresent(e::setDate);
            dto.getDescription().ifPresent(e::setDescription);
            e.setDateModified(LocalDate.now());
            e.setLastEditor(authenticationFacade.getAuthenticatedUser());
            return e;

        } catch (NotFoundException ex) {
            throw new UnprocessableEntityException(ex);
        }
    }
}
