package com.godev.budgetgo.business.user.impl;

import com.godev.budgetgo.api.rest.user.dto.UserCategoryCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.business.operation.CategoriesConverter;
import com.godev.budgetgo.business.operation.CategoriesDataService;
import com.godev.budgetgo.business.user.UserCategoriesConverter;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.domain.user.UserCategory;
import com.godev.budgetgo.domain.user.UserCategoryKey;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCategoriesConverterImpl implements UserCategoriesConverter {

    private final CategoriesConverter categoriesConverter;

    private final CategoriesDataService categoriesDataService;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public UserCategory convertToEntity(UserCategoryCreationDto dto) {
        UserCategory e = new UserCategory();
        e.setCategory(categoriesDataService.getById(dto.getCategoryId()));
        User user = authenticationFacade.getAuthenticatedUser();
        e.setUser(user);
        e.setId(new UserCategoryKey(user.getId(), dto.getCategoryId()));
        e.setUsedForIncomes(dto.isUsedForIncomes());
        e.setUsedForOutcomes(dto.isUsedForOutcomes());
        return e;
    }

    @Override
    public UserCategoryInfoDto convertToDto(UserCategory e) {
        UserCategoryInfoDto dto = new UserCategoryInfoDto();
        dto.setCategoryInfoDto(categoriesConverter.convertToDto(e.getCategory()));
        dto.setUsedForIncomes(e.isUsedForIncomes());
        dto.setUsedForOutcomes(e.isUsedForOutcomes());
        return dto;
    }

    @Override
    public UserCategory merge(UserCategory eOld, UserCategoryPatchesDto dto) {
        UserCategory e = eOld.cloneShallow();
        dto.getUsedForIncomes().ifPresent(e::setUsedForIncomes);
        dto.getUsedForOutcomes().ifPresent(e::setUsedForOutcomes);
        return e;
    }
}
