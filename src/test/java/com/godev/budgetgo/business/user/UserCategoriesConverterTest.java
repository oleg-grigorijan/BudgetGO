package com.godev.budgetgo.business.user;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.api.rest.operation.dto.CategoryInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.business.operation.CategoriesConverter;
import com.godev.budgetgo.business.operation.CategoriesDataService;
import com.godev.budgetgo.business.user.impl.UserCategoriesConverterImpl;
import com.godev.budgetgo.domain.operation.Category;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.domain.user.UserCategory;
import com.godev.budgetgo.domain.user.UserCategoryKey;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class UserCategoriesConverterTest {

    private UserCategoriesConverter converter;

    @Mock
    private CategoriesConverter categoriesConverter;

    @Mock
    private CategoriesDataService categoriesDataService;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @BeforeEach
    void setUp() {
        converter = new UserCategoriesConverterImpl(categoriesConverter, categoriesDataService, authenticationFacade);
    }

    @Test
    void convertToEntity_general_correctReturnValue() {
        UserCategoryCreationDto dto = new UserCategoryCreationDto();
        dto.setCategoryId(1L);
        dto.setUsedForIncomes(false);
        dto.setUsedForOutcomes(false);
        assertThat(dto).hasNoNullFieldsOrProperties();

        Category category = new Category();
        category.setId(dto.getCategoryId());
        when(categoriesDataService.getById(dto.getCategoryId())).thenReturn(category);
        User authenticatedUser = new User();
        authenticatedUser.setId(2L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        UserCategory expectedEntity = new UserCategory();
        expectedEntity.setId(new UserCategoryKey(authenticatedUser.getId(), category.getId()));
        expectedEntity.setCategory(category);
        expectedEntity.setUser(authenticatedUser);
        expectedEntity.setUsedForIncomes(dto.isUsedForIncomes());
        expectedEntity.setUsedForOutcomes(dto.isUsedForOutcomes());

        assertThat(converter.convertToEntity(dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void convertToDto_general_correctReturnValue() {
        UserCategory entity = new UserCategory();
        entity.setUser(new User());
        entity.getUser().setId(1L);
        entity.setCategory(new Category());
        entity.getCategory().setId(2L);
        entity.setUsedForIncomes(false);
        entity.setUsedForOutcomes(false);
        entity.setId(new UserCategoryKey(entity.getUser().getId(), entity.getCategory().getId()));
        assertThat(entity).hasNoNullFieldsOrProperties();

        CategoryInfoDto categoryInfoDto = new CategoryInfoDto();
        categoryInfoDto.setId(entity.getCategory().getId());
        when(categoriesConverter.convertToDto(entity.getCategory())).thenReturn(categoryInfoDto);

        UserCategoryInfoDto expectedDto = new UserCategoryInfoDto();
        expectedDto.setCategoryInfoDto(categoryInfoDto);
        expectedDto.setUsedForIncomes(entity.isUsedForIncomes());
        expectedDto.setUsedForOutcomes(entity.isUsedForOutcomes());
        assertThat(expectedDto).hasNoNullFieldsOrProperties();

        assertThat(converter.convertToDto(entity)).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void merge_general_correctReturnValue() {
        UserCategory entity = new UserCategory();
        entity.setUser(new User());
        entity.getUser().setId(1L);
        entity.setCategory(new Category());
        entity.getCategory().setId(2L);
        entity.setUsedForIncomes(false);
        entity.setUsedForOutcomes(false);
        entity.setId(new UserCategoryKey(entity.getUser().getId(), entity.getCategory().getId()));
        assertThat(entity).hasNoNullFieldsOrProperties();

        UserCategoryPatchesDto dto = new UserCategoryPatchesDto();
        dto.setUsedForIncomes(true);
        dto.setUsedForOutcomes(true);

        UserCategory expectedEntity = entity.cloneShallow();
        expectedEntity.setUsedForIncomes(dto.getUsedForIncomes().get());
        expectedEntity.setUsedForOutcomes(dto.getUsedForOutcomes().get());
        assertThat(expectedEntity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void merge_emptyPatchesDto_noEntityChanges() {
        UserCategory entity = new UserCategory();
        entity.setUser(new User());
        entity.getUser().setId(1L);
        entity.setCategory(new Category());
        entity.getCategory().setId(2L);
        entity.setUsedForIncomes(false);
        entity.setUsedForOutcomes(false);
        entity.setId(new UserCategoryKey(entity.getUser().getId(), entity.getCategory().getId()));
        assertThat(entity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, new UserCategoryPatchesDto())).isEqualToComparingFieldByField(entity);
    }
}
