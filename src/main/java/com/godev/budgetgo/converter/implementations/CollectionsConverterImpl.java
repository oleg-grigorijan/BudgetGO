package com.godev.budgetgo.converter.implementations;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.CategoriesConverter;
import com.godev.budgetgo.converter.CollectionsConverter;
import com.godev.budgetgo.data.CategoriesDataService;
import com.godev.budgetgo.dto.CollectionCreationDto;
import com.godev.budgetgo.dto.CollectionInfoDto;
import com.godev.budgetgo.entity.Collection;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategoryKey;
import org.springframework.stereotype.Service;

@Service
class CollectionsConverterImpl implements CollectionsConverter {

    private final CategoriesConverter categoriesConverter;
    private final CategoriesDataService categoriesDataService;
    private final AuthenticationFacade authenticationFacade;

    public CollectionsConverterImpl(
            CategoriesConverter categoriesConverter,
            CategoriesDataService categoriesDataService,
            AuthenticationFacade authenticationFacade
    ) {
        this.categoriesConverter = categoriesConverter;
        this.categoriesDataService = categoriesDataService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Collection convertFromDto(CollectionCreationDto dto) {
        Collection e = new Collection();
        e.setCategory(categoriesDataService.getById(dto.getCategoryId()));
        User user = authenticationFacade.getAuthenticatedUser();
        e.setUser(user);
        e.setId(new UserCategoryKey(user.getId(), dto.getCategoryId()));
        return e;
    }

    @Override
    public CollectionInfoDto convertFromEntity(Collection e) {
        CollectionInfoDto dto = new CollectionInfoDto();
        dto.setCategoryInfoDto(categoriesConverter.convertFromEntity(e.getCategory()));
        return dto;
    }
}
