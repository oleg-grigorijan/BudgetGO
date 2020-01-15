package com.godev.budgetgo.converter;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.impl.OperationsConverterImpl;
import com.godev.budgetgo.data.CategoriesDataService;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageOperationKey;
import com.godev.budgetgo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class OperationsConverterTest {

    private OperationsConverter converter;

    @Mock
    private StoragesDataService storagesDataService;

    @Mock
    private CategoriesDataService categoriesDataService;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private CategoriesConverter categoriesConverter;

    @Mock
    private UsersConverter usersConverter;

    @BeforeEach
    void setUp() {
        converter = new OperationsConverterImpl(
                storagesDataService,
                categoriesDataService,
                authenticationFacade,
                categoriesConverter,
                usersConverter,
                Clock.fixed(Instant.EPOCH, ZoneId.systemDefault())
        );
    }

    @Test
    void convertToEntity_general_correctReturnValue() {
        ExtendedOperationCreationDto dto = new ExtendedOperationCreationDto();
        dto.setStorageId(1L);
        dto.setCategoryId(2L);
        dto.setMoneyDelta(1000L);
        dto.setDate(LocalDate.EPOCH.plusDays(1));
        dto.setDescription("abc");
        assertThat(dto).hasNoNullFieldsOrProperties();

        Storage storage = new Storage();
        storage.setId(dto.getStorageId());
        when(storagesDataService.getById(dto.getStorageId())).thenReturn(storage);
        Category category = new Category();
        category.setId(dto.getCategoryId());
        when(categoriesDataService.getById(dto.getCategoryId())).thenReturn(category);
        User authenticatedUser = new User();
        authenticatedUser.setId(3L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        Operation expectedEntity = new Operation();
        expectedEntity.setStorage(storage);
        expectedEntity.setCategory(category);
        expectedEntity.setMoneyDelta(dto.getMoneyDelta());
        expectedEntity.setDate(dto.getDate());
        expectedEntity.setDescription(dto.getDescription());
        expectedEntity.setDateCreated(LocalDate.EPOCH);
        expectedEntity.setDateModified(LocalDate.EPOCH);
        expectedEntity.setCreator(authenticatedUser);
        expectedEntity.setLastEditor(authenticatedUser);
        assertThat(expectedEntity).hasNoNullFieldsOrPropertiesExcept("id");

        assertThat(converter.convertToEntity(dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void convertToDto_general_correctReturnValue() {
        Operation entity = new Operation();
        Storage storage = new Storage();
        storage.setId(1L);
        entity.setId(new StorageOperationKey(storage.getId(), 2L));
        entity.setStorage(storage);
        Category category = new Category();
        category.setId(3L);
        entity.setCategory(category);
        entity.setMoneyDelta(1000L);
        entity.setDate(LocalDate.EPOCH.plusDays(1));
        entity.setDescription("abc");
        entity.setDateCreated(LocalDate.EPOCH.plusDays(2));
        entity.setDateModified(LocalDate.EPOCH.plusDays(3));
        User creator = new User();
        creator.setId(4L);
        entity.setCreator(creator);
        User lastEditor = new User();
        lastEditor.setId(5L);
        entity.setLastEditor(lastEditor);
        assertThat(entity).hasNoNullFieldsOrProperties();

        CategoryInfoDto categoryInfoDto = new CategoryInfoDto();
        categoryInfoDto.setId(category.getId());
        when(categoriesConverter.convertToDto(entity.getCategory())).thenReturn(categoryInfoDto);
        UserInfoDto lastEditorInfoDto = new UserInfoDto();
        UserInfoDto creatorInfoDto = new UserInfoDto();
        when(usersConverter.convertToDto(entity.getCreator())).thenReturn(creatorInfoDto);
        lastEditorInfoDto.setId(lastEditor.getId());
        when(usersConverter.convertToDto(entity.getLastEditor())).thenReturn(lastEditorInfoDto);

        OperationInfoDto expectedDto = new OperationInfoDto();
        expectedDto.setId(entity.getId().getOperationId());
        expectedDto.setCategoryInfoDto(categoryInfoDto);
        expectedDto.setMoneyDelta(entity.getMoneyDelta());
        expectedDto.setDate(entity.getDate());
        expectedDto.setDescription(entity.getDescription());
        expectedDto.setDateCreated(entity.getDateCreated());
        expectedDto.setDateModified(entity.getDateModified());
        expectedDto.setCreatorInfoDto(creatorInfoDto);
        expectedDto.setLastEditorInfoDto(lastEditorInfoDto);
        assertThat(expectedDto).hasNoNullFieldsOrProperties();

        assertThat(converter.convertToDto(entity)).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void merge_general_correctReturnValue() {
        Operation entity = new Operation();
        Storage storage = new Storage();
        storage.setId(1L);
        entity.setId(new StorageOperationKey(storage.getId(), 2L));
        entity.setStorage(storage);
        Category category = new Category();
        category.setId(3L);
        entity.setCategory(category);
        entity.setMoneyDelta(1000L);
        entity.setDate(LocalDate.EPOCH.plusDays(1));
        entity.setDescription("abc");
        entity.setDateCreated(LocalDate.EPOCH.plusDays(2));
        entity.setDateModified(LocalDate.EPOCH.plusDays(3));
        User creator = new User();
        creator.setId(4L);
        entity.setCreator(creator);
        User lastEditor = new User();
        lastEditor.setId(5L);
        entity.setLastEditor(lastEditor);
        assertThat(entity).hasNoNullFieldsOrProperties();

        OperationPatchesDto dto = new OperationPatchesDto();
        dto.setCategoryId(6L);
        dto.setMoneyDelta(2000L);
        dto.setDate(LocalDate.EPOCH.plusDays(4));
        dto.setDescription("cba");

        Category newCategory = new Category();
        newCategory.setId(dto.getCategoryId().get());
        when(categoriesDataService.getById(dto.getCategoryId().get())).thenReturn(newCategory);
        User authenticatedUser = new User();
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        Operation expectedEntity = new Operation();
        expectedEntity.setId(entity.getId());
        expectedEntity.setStorage(storage);
        expectedEntity.setCategory(newCategory);
        expectedEntity.setMoneyDelta(dto.getMoneyDelta().get());
        expectedEntity.setDate(dto.getDate().get());
        expectedEntity.setDescription(dto.getDescription().get());
        expectedEntity.setDateCreated(entity.getDateCreated());
        expectedEntity.setDateModified(LocalDate.EPOCH);
        expectedEntity.setCreator(entity.getCreator());
        expectedEntity.setLastEditor(authenticatedUser);
        assertThat(expectedEntity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void merge_emptyPatchesDto_modificationInfoChangeOnly() {
        Operation entity = new Operation();
        Storage storage = new Storage();
        storage.setId(1L);
        entity.setId(new StorageOperationKey(storage.getId(), 2L));
        entity.setStorage(storage);
        Category category = new Category();
        category.setId(3L);
        entity.setCategory(category);
        entity.setMoneyDelta(1000L);
        entity.setDate(LocalDate.EPOCH.plusDays(1));
        entity.setDescription("abc");
        entity.setDateCreated(LocalDate.EPOCH.plusDays(2));
        entity.setDateModified(LocalDate.EPOCH.plusDays(3));
        User creator = new User();
        creator.setId(4L);
        entity.setCreator(creator);
        User lastEditor = new User();
        lastEditor.setId(5L);
        entity.setLastEditor(lastEditor);
        assertThat(entity).hasNoNullFieldsOrProperties();

        User authenticatedUser = new User();
        authenticatedUser.setId(6L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        Operation expectedEntity = entity.cloneShallow();
        expectedEntity.setDateModified(LocalDate.EPOCH);
        expectedEntity.setLastEditor(authenticatedUser);
        assertThat(expectedEntity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, new OperationPatchesDto())).isEqualToComparingFieldByField(expectedEntity);
    }
}
