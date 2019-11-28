package com.godev.budgetgo.converter.implementations;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.CategoriesConverter;
import com.godev.budgetgo.converter.UserCategoriesConverter;
import com.godev.budgetgo.data.CategoriesDataService;
import com.godev.budgetgo.dto.UserCategoryCreationDto;
import com.godev.budgetgo.dto.UserCategoryInfoDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategory;
import com.godev.budgetgo.entity.UserCategoryKey;
import org.springframework.stereotype.Service;

@Service
class UserCategoriesConverterImpl implements UserCategoriesConverter {

    private final CategoriesConverter categoriesConverter;
    private final CategoriesDataService categoriesDataService;
    private final AuthenticationFacade authenticationFacade;

    public UserCategoriesConverterImpl(
            CategoriesConverter categoriesConverter,
            CategoriesDataService categoriesDataService,
            AuthenticationFacade authenticationFacade
    ) {
        this.categoriesConverter = categoriesConverter;
        this.categoriesDataService = categoriesDataService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public UserCategory convertFromDto(UserCategoryCreationDto dto) {
        UserCategory e = new UserCategory();
        e.setCategory(categoriesDataService.getById(dto.getCategoryId()));
        User user = authenticationFacade.getAuthenticatedUser();
        e.setUser(user);
        e.setId(new UserCategoryKey(user.getId(), dto.getCategoryId()));
        return e;
    }

    @Override
    public UserCategoryInfoDto convertFromEntity(UserCategory e) {
        UserCategoryInfoDto dto = new UserCategoryInfoDto();
        dto.setCategoryInfoDto(categoriesConverter.convertFromEntity(e.getCategory()));
        return dto;
    }
}
