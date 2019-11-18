package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.CollectionCreationDto;
import com.godev.budgetgo.entity.Collection;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategoryKey;
import com.godev.budgetgo.service.data.CategoriesDataService;
import com.godev.budgetgo.service.factory.CollectionsFactory;
import org.springframework.stereotype.Service;

@Service
class CollectionsFactoryImpl implements CollectionsFactory {

    private final CategoriesDataService categoriesDataService;
    private final AuthenticationFacade authenticationFacade;

    public CollectionsFactoryImpl(CategoriesDataService categoriesDataService, AuthenticationFacade authenticationFacade) {
        this.categoriesDataService = categoriesDataService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Collection createFrom(CollectionCreationDto dto) {
        Collection e = new Collection();
        e.setCategory(categoriesDataService.getById(dto.getCategoryId()));
        User user = authenticationFacade.getAuthenticatedUser();
        e.setUser(user);
        e.setId(new UserCategoryKey(user.getId(), dto.getCategoryId()));
        return e;
    }
}
